package org.gerrymc.springbootdemo.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.gerrymc.springbootdemo.aspect.AccessTokenVerify;
import org.gerrymc.springbootdemo.dto.resultful.ResultDTO;
import org.gerrymc.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GerryMC
 * @description 用户管理Api接口
 * @create 2021/8/20
 **/
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*---------------------用户通用接口-------------------*/
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResultDTO login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {

        //返回accessToken结果
        String accessToken = userService.login(username, password);
        Map<String, Object> map = new HashMap<>();
        map.put("accesstoken", accessToken);
        return ResultDTO.success(map);
    }

    /**
     * 用户修改密码
     * @param accessToken
     * @param password
     * @return
     */
    @PostMapping("/modify-password")
    @AccessTokenVerify
    public ResultDTO modifyPWD(@RequestHeader("accesstoken") String accessToken,
                               @RequestParam("password") String password) {
        userService.modifyPWD(accessToken, password);
        return ResultDTO.success(null);
    }


    /*---------------------管理角色接口(目前仅超管可管理以下功能，其他角色可类似调整)-------------------*/

    /**
     * 管理添加用户
     * @param accessToken
     * @param username
     * @param name
     * @param companyId
     * @param password
     * @return
     */
    @PostMapping("/add-user")
    @AccessTokenVerify
    public ResultDTO addUser(@RequestHeader("accesstoken") String accessToken,
                             @RequestParam("username") String username,
                             @RequestParam("name") String name,
                             @RequestParam("companyid") String companyId,
                             @RequestParam("password") String password) {

        userService.verSystemAdmin(accessToken); //验证是否为超管(验证失败会直接异常处理并返回结果)
        userService.addUser(username, name, companyId, password);
        return ResultDTO.success(null);
    }

    /**
     * 管理修改用户信息
     * @param id
     * @param username
     * @param name
     * @return
     */
    @PostMapping("/update-user")
    @AccessTokenVerify
    public ResultDTO updateUser(@RequestHeader("accesstoken") String accessToken,
                                @RequestParam("id") Integer id,
                                @RequestParam("username") String username,
                                @RequestParam("name") String name) {
        userService.verSystemAdmin(accessToken); //验证是否为超管(验证失败会直接异常处理并返回结果)
        userService.updateUser(id, username, name);
        return ResultDTO.success(null);
    }

    /**
     * 删除用户（暂未开发）
     * @param accessToken
     * @param id
     * @return
     */
    /*@PostMapping("/delete-user")
    @AccessTokenVerify
    public ResultDTO deleteUser(@RequestHeader("accesstoken") String accessToken,
                                @RequestParam("id") Integer id) {
        userService.verSystemAdmin(accessToken); //验证是否为超管(验证失败会直接异常处理并返回结果)
        return ResultDTO.success(null);
    }*/

    /**
     * 重置用户密码
     * @param accessToken
     * @return
     */
    @PostMapping("/reset-password")
    @AccessTokenVerify
    public ResultDTO resetPWD(@RequestHeader("accesstoken") String accessToken,
                              @RequestParam("id") Integer id) {
        userService.verSystemAdmin(accessToken); //验证是否为超管(验证失败会直接异常处理并返回结果)
        userService.resetPWD(accessToken, id);
        return ResultDTO.success(null);
    }
}
