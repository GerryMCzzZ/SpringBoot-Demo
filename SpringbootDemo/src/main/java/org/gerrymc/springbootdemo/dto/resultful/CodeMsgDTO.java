package org.gerrymc.springbootdemo.dto.resultful;

/**
 * @author GerryMC
 * @Description 结果返回码、信息说明管理
 * @date 2021/8/19
 */
public class CodeMsgDTO {
    private int code;
    private String message;

    /**返回请求代码及说明*/
    public static CodeMsgDTO SUCCESS = new CodeMsgDTO(200, "success");
    public static CodeMsgDTO ERROR = new CodeMsgDTO(500, "服务端异常");
    public static CodeMsgDTO TOKEN_ERROR = new CodeMsgDTO(4001, "token验证错误");
    public static CodeMsgDTO SYSTEM_UNKNOWN_EXCEPTION = new CodeMsgDTO(4002, "系统未知异常");

    /**返回请求的数据代码及说明*/
    public static CodeMsgDTO RESULT_FAILED = new CodeMsgDTO(0, "失败");
    public static CodeMsgDTO RESULT_SUCCESS = new CodeMsgDTO(1, "成功");
    public static CodeMsgDTO NAME_EXISTS = new CodeMsgDTO(2, "名称已存在");
    public static CodeMsgDTO USER_EXISTS = new CodeMsgDTO(2, "用户已存在");
    public static CodeMsgDTO PHONE_EXISTS = new CodeMsgDTO(3, "电话已存在");
    public static CodeMsgDTO NO_ACCESS = new CodeMsgDTO(4, "无权限");
    public static CodeMsgDTO USER_INFO_ERROR = new CodeMsgDTO(5, "用户信息错误");
    public static CodeMsgDTO PARAM_ERROR = new CodeMsgDTO(6, "传入参数有误");
    public static CodeMsgDTO IDCARD_ERROR = new CodeMsgDTO(7, "身份证有误");
    public static CodeMsgDTO NO_AUTH = new CodeMsgDTO(8, "无权限修改");

    public CodeMsgDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
