package com.project.ordersystem.exception;

import org.springframework.http.HttpStatus;

public class BuyerNotFoundException extends BaseException {

    public BuyerNotFoundException(String messageKey) {
        this.code = HttpStatus.OK;
        this.messageKey = messageKey;
    }
}
