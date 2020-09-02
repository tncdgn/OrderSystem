package com.project.ordersystem.controller;

import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.response.BaseResponse;
import com.project.ordersystem.response.CreateOrderResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends BaseController {

    @ApiOperation("Create Order")
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResponse> createOrder(@RequestBody CreateOrderModel createOrderModel) {
        ResponseEntity<BaseResponse> response = null;
        BaseResponse productResponse = new CreateOrderResponse();
        try {
            orderValidator.validate(createOrderModel);
            orderService.createOrder(createOrderModel);
            response = new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }
}
