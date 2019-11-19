/**
 * @author : 孙留平
 * @since : 2018年9月7日 下午2:06:25
 * @see:
 */
package com.tianque.commons.util.exception.base.handler;

import com.tianque.commons.util.exception.domain.Result;

/**
 * @author : Administrator
 * @since : 2018年9月7日 下午2:06:25
 * @see :
 */
public class BusinessValidationExceptionHandler implements ExceptionHandler {

    @Override
    public Result handleException(String errorCode, Exception bex,
            Result result) {
        result.setMessage(bex.getMessage());
        result.setErrorCode(errorCode);
        return result;
    }

}