package com.example.shop.exception.handler;

import com.example.shop.enums.ResultEnum;
import com.example.shop.exception.domain.ApiException;
import com.example.shop.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ly
 * @date 2020/01/10
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    /**
     * 异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult apiExceptionHandler(Exception e) {
        log.error("[请求失败] {}\n\r", e.getMessage());
        e.printStackTrace();
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            return ApiResult.error(exception.getCode(), exception.getMessage());
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) e;
            return ApiResult.error(ResultEnum.PARAMS_ERR);
        }
        return ApiResult.error(ResultEnum.UNKNOWN_ERR);
    }
}
