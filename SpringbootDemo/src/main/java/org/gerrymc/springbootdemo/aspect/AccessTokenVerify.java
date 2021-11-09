package org.gerrymc.springbootdemo.aspect;

import java.lang.annotation.*;

/**
 * @author GerryMC
 * @Description 用户token验证注解
 * @date 2021/11/8
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessTokenVerify {
}
