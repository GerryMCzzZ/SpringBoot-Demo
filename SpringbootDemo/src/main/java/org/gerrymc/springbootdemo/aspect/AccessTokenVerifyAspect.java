package org.gerrymc.springbootdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.gerrymc.springbootdemo.enums.ResultEnum;
import org.gerrymc.springbootdemo.exception.SpringbootDemoException;
import org.gerrymc.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GerryMC
 * @Description 验证用户token
 * @date 2021/11/8
 */
@Aspect
@Component
@Order(1)
public class AccessTokenVerifyAspect {
    @Autowired
    private UserService userService;

    @Pointcut(value = "@annotation(org.gerrymc.springbootdemo.aspect.AccessTokenVerify)")
    public void verify(){};

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        String accesstoken = request.getHeader("accesstoken");
        if(accesstoken == null || accesstoken.equals("")){
            throw new SpringbootDemoException(ResultEnum.TOKEN_VERIFY_ERROR);
        }

        //验证token
        userService.verToken(accesstoken);
    }
}