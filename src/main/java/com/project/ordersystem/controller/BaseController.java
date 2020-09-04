package com.project.ordersystem.controller;

import com.project.ordersystem.exception.BaseException;
import com.project.ordersystem.response.BaseResponse;
import com.project.ordersystem.response.ExceptionResponse;
import com.project.ordersystem.service.CategoryService;
import com.project.ordersystem.service.MessageResourceService;
import com.project.ordersystem.service.OrderService;
import com.project.ordersystem.service.ProductService;
import com.project.ordersystem.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    @Autowired
    private MessageResourceService messageResourceService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected OrderValidator orderValidator;

    @Autowired
    protected OrderService orderService;

    protected ResponseEntity<BaseResponse> handleException(Exception e) {
        String message;
        int responseCode;

        if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            message = messageResourceService.getMessage(ex.getMessageKey());
            responseCode = ex.getCode().value();
        } else {
            message = e.getMessage();
            responseCode = HttpStatus.BAD_REQUEST.value();
        }

        ExceptionResponse exceptionResponse = createExceptionResponse(responseCode, message);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionResponse createExceptionResponse(int responseCode, String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setResponseCode(responseCode);
        exceptionResponse.setMessage(message);

        return exceptionResponse;
    }
}
