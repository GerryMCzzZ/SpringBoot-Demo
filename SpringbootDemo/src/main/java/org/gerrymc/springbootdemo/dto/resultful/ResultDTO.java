package org.gerrymc.springbootdemo.dto.resultful;

import lombok.Data;

/**
 * @author GerryMC
 * @Description 返回结果封装
 * @date 2021/8/19
 */
@Data
public class ResultDTO<T> {

    private int code; //返回代码
    private String message;  //返回说明
    private T data;  //返回数据

    //成功时候的调用
    public static <T> ResultDTO<T> success(T data){
        return new ResultDTO<T>(data);
    }

    //失败时候的调用
    public static <T> ResultDTO<T> error(CodeMsgDTO codeMsg){
        return new  ResultDTO<T>(codeMsg);
    }

    //成功时只设置消息
    private ResultDTO(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    //将返回消息CodeMsg封装到本类中
    public ResultDTO(CodeMsgDTO codeMsg) {
        if(codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMsg();
    }

    //将返回消息,以及数据
    public ResultDTO(CodeMsgDTO codeMsg, T date) {
        if(codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMsg();
        this.data = date;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }

}