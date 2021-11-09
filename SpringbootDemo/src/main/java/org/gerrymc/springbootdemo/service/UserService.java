package org.gerrymc.springbootdemo.service;

import org.gerrymc.springbootdemo.entity.system.SdSystemInfoUsers;

/**
 * @author GerryMC
 * @description 用户管理服务层
 * @create 2021/8/20 9:20
 **/
public interface UserService {

    /**
     * 登录
     * @author GerryMC
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     * @author GerryMC
     * @param accessToken
     * @return RsSystemInfoUsers
     */
    SdSystemInfoUsers getUserInfo(String accessToken);

    /**
     * 验证toke码
     * @author GerryMC
     * @param accessToken
     * @return
     */
    void verToken(String accessToken);

    /**
     * 用户修改密码
     * @author GerryMC
     * @param accessToken
     * @param pwd
     */
    void modifyPWD(String accessToken, String pwd);

    /**
     * @Author GerryMC
     * @Description 新增用户
     * @Date 2021/11/9 14:32
     * @Param [username, name, companyId, password]
     * @return void
     **/
    void addUser(String username, String name, String companyId, String password);

    /**
     * @Author GerryMC
     * @Description 修改用户
     * @Date 2021/11/9 14:33
     * @Param [id, username, name]
     * @return void
     **/
    void updateUser(Integer id, String username, String name);



    /**
     * 验证是否是超级管理员
     * @author GerryMC
     * @param accessToken
     */
    void verSystemAdmin(String accessToken);

    /**
     * 验证是否是公司管理员
     * @author GerryMC
     * @param accessToken
     */
    void verCompanyAdmin(String accessToken);


    /**
     * 验证是否是教师
     * @author GerryMC
     * @param accessToken
     */
    void verTeacher(String accessToken);

    /**
     * 获取用户公司id
     * @param accessToken
     * @return 公司id
     */
    Integer getUserCompanyId(String accessToken);

    /**
     * 重置密码
     * @param accessToken
     * @param id
     */
    void resetPWD(String accessToken, Integer id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    SdSystemInfoUsers getUserById(Integer id);
}
