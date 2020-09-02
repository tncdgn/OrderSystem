package com.project.ordersystem.exception;

import org.springframework.http.HttpStatus;

public class WareHouseAddressNotFoundException extends BaseException {

    public WareHouseAddressNotFoundException(String messageKey) {
        this.code = HttpStatus.OK;
        this.messageKey = messageKey;
    }
}
