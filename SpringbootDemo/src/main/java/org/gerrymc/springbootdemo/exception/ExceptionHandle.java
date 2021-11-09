package org.gerrymc.springbootdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.gerrymc.springbootdemo.dto.resultful.CodeMsgDTO;
import org.gerrymc.springbootdemo.dto.resultful.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GerryMC
 * @Description 全局异常类处理
 * @date 2021/8/19
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDTO handlerException(Exception e){
        if (e instanceof SpringbootDemoException) {
            SpringbootDemoException examToolsException = (SpringbootDemoException) e;
            log.info(e.getMessage());
            return ResultDTO.error(new CodeMsgDTO(examToolsException.getCode(),e.getMessage()));
        }else {
            log.info("系统未知错误，请注意查看。异常= " + e.getMessage());
            return ResultDTO.error(CodeMsgDTO.SYSTEM_UNKNOWN_EXCEPTION);
        }
    }
}
