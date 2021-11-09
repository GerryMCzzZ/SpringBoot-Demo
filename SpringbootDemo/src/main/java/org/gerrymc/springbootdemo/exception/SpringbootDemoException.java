package org.gerrymc.springbootdemo.exception;


import org.gerrymc.springbootdemo.enums.ResultEnum;

/**
 * @author GerryMC
 * @Description 全局异常处理
 * @date 2021/8/19
 */
public class SpringbootDemoException extends RuntimeException {
    private Integer code;

    public SpringbootDemoException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
