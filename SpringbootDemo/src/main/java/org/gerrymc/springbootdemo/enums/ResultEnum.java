package org.gerrymc.springbootdemo.enums;

import lombok.Getter;

/**
 * @author GerryMC
 * @Description 返回结果枚举
 * @date 2021/8/19
 */
@Getter
public enum ResultEnum {
    /**
     * 系统模块
     * */
    EXPORT_ERR(7, "导出mdb失败，数据为空"),
    NO_AUTH(8, "无权限修改"),
    SAVE_ERROR(10, "存储数据失败"),
    USER_LOGIN_ERROR(11, "用户账户或密码错误"),
    SMS_PHONE_CODE(12, "手机发送验证码异常"),
    FILE_IS_NULL(15, "文件为空"),
    FILE_FORMAT_ERROR(16, "文件格式不支持"),
    FILE_UPLOAD_ERROR(17, "文件上传异常"),
    TOKEN_VERIFY_ERROR(21,"用户token码验证异常"),


    /**
     * 公司模块
     */
    COMPANY_EXIT(31, "公司已存在"),
    COMPANY_NOT_EXIT(32, "公司不存在"),


    /**
     * 用户模块
     */
    USER_EXIT(41,"用户已存在"),
    USER_NOT_EXIT(42,"用户不存在"),
    USER_NOT_SYSTEM_ADMIN(51,"用户不是超级管理员"),
    USER_NOT_COMPANY_ADMIN(52,"用户不是公司管理员"),
    USER_NOT_TEACHER(53,"用户不是教师"),
    USER_NO_AUTH(54, "当前用户无权限"),
    USER_SELECT_ERR(55, "被重置密码用户选择错误，请检查信息后重试"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
