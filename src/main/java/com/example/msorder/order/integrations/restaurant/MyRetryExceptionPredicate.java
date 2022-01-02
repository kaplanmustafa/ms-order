package com.example.msorder.order.integrations.restaurant;

import com.example.mscommon.error.ErrorObj;
import com.example.mscommon.error.feign.MyFeignClientException;

import java.util.function.Predicate;

public class MyRetryExceptionPredicate implements Predicate<Exception> {

    @Override
    public boolean test(Exception e) {

        if (e instanceof MyFeignClientException) {
            MyFeignClientException exception = (MyFeignClientException) e;
            ErrorObj errorObj = exception.getErrorObj();

            if (errorObj.getErrorCode() == 19000) {
                return true;
            }
        }

        return false;
    }
}
