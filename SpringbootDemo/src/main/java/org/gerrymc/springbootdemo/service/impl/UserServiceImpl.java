package org.gerrymc.springbootdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.gerrymc.springbootdemo.dao.system.SdSystemInfoUsersDao;
import org.gerrymc.springbootdemo.entity.system.SdSystemInfoUsers;
import org.gerrymc.springbootdemo.enums.ResultEnum;
import org.gerrymc.springbootdemo.enums.RoleEnum;
import org.gerrymc.springbootdemo.exception.SpringbootDemoException;
import org.gerrymc.springbootdemo.service.UserService;
import org.gerrymc.springbootdemo.utils.daily.MD5Util;
import org.gerrymc.springbootdemo.utils.daily.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author GerryMC
 * @description 用户管理服务实现层
 * @create 2021/8/19 16:15
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    //用户数据操作
    @Autowired
    private SdSystemInfoUsersDao systemInfoUsersDao;

    /**
     * @Author GerryMC
     * @Description 登录
     * @Date 2021/11/9 14:36
     * @Param [username, password]
     * @return java.lang.String
     **/
    @Override
    public String login(String username, String password) {
        //根据账户查询用户
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByUsername(username);
        //判断账户是否存在且密码是否正确
        if (infoUsers != null && infoUsers.getPassword().equals(MD5Util.pwdMD5(password))) {
            //正确就重新生成token
            String accessToken = TokenUtil.getToken(username);
            infoUsers.setTokenCode(accessToken);//
            infoUsers.setTokenTimeout(TokenUtil.flushTokenTimeout());//刷新token过期时间
            systemInfoUsersDao.save(infoUsers);
            return accessToken;
        } else {
            throw new SpringbootDemoException(ResultEnum.USER_LOGIN_ERROR);
        }
    }

    /**
     * @Author GerryMC
     * @Description 获取用户信息
     * @Date 2021/11/9 14:41
     * @Param [accessToken]
     * @return org.gerrymc.springbootdemo.entity.system.RsSystemInfoUsers
     **/
    @Override
    public SdSystemInfoUsers getUserInfo(String accessToken) {
        return systemInfoUsersDao.findByTokenCode(accessToken);
    }

    /**
     * @Author GerryMC
     * @Description 验证toke码
     * @Date 2021/11/9 14:37
     * @Param [accessToken]
     * @return void
     **/
    @Override
    public void verToken(String accessToken) {
        //查询用户
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByTokenCode(accessToken);
        //判断用户是否存在
        if (infoUsers != null) {
            //获取当前时间
            String currentTimeMillis = System.currentTimeMillis() + "";

            //判断时间是否过期
            if (infoUsers.getTokenTimeout().compareTo(currentTimeMillis) > 0) {
                //没过期
                infoUsers.setTokenTimeout(TokenUtil.flushTokenTimeout());//刷新token过期时间
                systemInfoUsersDao.save(infoUsers);
                return;
            }

        }

        // 清除token和过期时间
        //clearTokenAndTimeout(accessToken);

        //验证失败抛出异常结果
        throw new SpringbootDemoException(ResultEnum.TOKEN_VERIFY_ERROR);
    }

    /**
     * 用户修改密码
     *
     * @param accessToken
     * @param pwd
     */
    @Override
    public void modifyPWD(String accessToken, String pwd) {
        //查询用户
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByTokenCode(accessToken);

        infoUsers.setPassword(MD5Util.pwdMD5(pwd));
        SdSystemInfoUsers result = systemInfoUsersDao.save(infoUsers);
        if (result == null) {
            throw new SpringbootDemoException(ResultEnum.SAVE_ERROR);
        }
    }


    /**
     * 添加用户(默认新增新增公司角色)
     *
     * @param username
     * @param name
     */
    @Override
    public void addUser(String username, String name, String companyId, String password) {
        //查询新增用户是否存在（不能重复账户名）
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByUsername(username);
        if (infoUsers == null) {
            //设置参数
            SdSystemInfoUsers user = new SdSystemInfoUsers();
            user.setUsername(username);
            user.setPassword(MD5Util.pwdMD5(password));
            user.setName(name);
            user.setCompanyId(Integer.parseInt(companyId));
            user.setRoleId(RoleEnum.COMPANY_ADMIN.getCode());
            //保存
            systemInfoUsersDao.save(user);
        } else {
            //验证失败抛出异常结果
            throw new SpringbootDemoException(ResultEnum.USER_EXIT);
        }
    }

    /**
     * 修改用户
     *
     * @param id
     * @param username
     * @param name
     */
    @Override
    public void updateUser(Integer id, String username, String name) {
        //查询用户
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findById(id).get();
        //判断用户是否存在
        if (infoUsers != null) {
            //设置参数
            infoUsers.setUsername(username);
            infoUsers.setName(name);
            //保存
            systemInfoUsersDao.save(infoUsers);
        } else {
            //验证失败抛出异常结果
            throw new SpringbootDemoException(ResultEnum.USER_NOT_EXIT);
        }
    }

    /**
     * 验证是否是超级管理员
     *
     * @param accessToken
     */
    @Override
    public void verSystemAdmin(String accessToken) {
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByTokenCode(accessToken);
        if (infoUsers.getRoleId() != RoleEnum.SYSTEM_ADMIN.getCode()) {
            throw new SpringbootDemoException(ResultEnum.USER_NOT_SYSTEM_ADMIN);
        }
    }


    /**
     * 验证是否是公司管理员
     *
     * @param accessToken
     */
    @Override
    public void verCompanyAdmin(String accessToken) {
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByTokenCode(accessToken);
        if (infoUsers.getRoleId() != RoleEnum.COMPANY_ADMIN.getCode()) {
            throw new SpringbootDemoException(ResultEnum.USER_NOT_COMPANY_ADMIN);
        }
    }

    /**
     * 验证是否是教师
     *
     * @param accessToken
     */
    @Override
    public void verTeacher(String accessToken) {
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByTokenCode(accessToken);
        if (infoUsers.getRoleId() != RoleEnum.TEACHER.getCode()) {
            throw new SpringbootDemoException(ResultEnum.USER_NOT_TEACHER);
        }
    }

    /**
     * 获取用户公司id
     * @param accessToken
     * @return 公司id
     */
    @Override
    public Integer getUserCompanyId(String accessToken) {
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findByTokenCode(accessToken);
        return infoUsers.getCompanyId();
    }

    /**
     * 重置密码
     * @param accessToken
     * @param id
     */
    @Override
    public void resetPWD(String accessToken, Integer id) {
        //查询用户
        SdSystemInfoUsers infoUsers = systemInfoUsersDao.findById(id).get();
        //校验被重置用户是否存在，不存在则抛出异常
        if(infoUsers == null){
            throw new SpringbootDemoException(ResultEnum.USER_NOT_EXIT);
        }
        //重置密码
        infoUsers.setPassword(MD5Util.pwdMD5("888888"));
        systemInfoUsersDao.save(infoUsers);
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public SdSystemInfoUsers getUserById(Integer id) {
        return systemInfoUsersDao.findById(id).orElse(null);
    }

}
