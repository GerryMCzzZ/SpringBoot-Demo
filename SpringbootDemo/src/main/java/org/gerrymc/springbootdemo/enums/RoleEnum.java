package org.gerrymc.springbootdemo.enums;

import lombok.Getter;

/**
 * @author GerryMC
 * @Description 角色枚举
 * @date 2021/8/19
 */
@Getter
public enum RoleEnum {
    /**
     * 角色
     * */
    SYSTEM_ADMIN(0),    //超级管理员
    COMPANY_ADMIN(1),   //公司管理
    TEACHER(2)          //教师
    ;

    private Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}
