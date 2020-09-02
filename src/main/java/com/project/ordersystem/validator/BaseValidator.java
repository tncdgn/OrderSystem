package com.project.ordersystem.validator;

import com.project.ordersystem.entity.Product;
import com.project.ordersystem.exception.InvalidRequestException;
import com.project.ordersystem.model.BaseModel;
import com.project.ordersystem.service.ProductService;
import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseValidator<T extends BaseModel> {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected ProductValidator productValidator;

    public abstract void validate(T request);

    protected void validateRequest(Object value, String messageKey, Matcher<?> matcher) {
        if (!matcher.matches(value)) {
            throw new InvalidRequestException(messageKey, value);
        }
    }
}
