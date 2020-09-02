package com.project.ordersystem.validator;

import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.model.ProductModel;
import com.project.ordersystem.validator.util.Validators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderValidator extends BaseValidator<CreateOrderModel> {

    @Override
    public void validate(CreateOrderModel request) {
        validateProductModels(request.getProductModels());
        validateBuyer(request.getBuyerId());
        validateWareHouse(request.getBuyerWareHouseAddressId());
    }


    private void validateProductModels(List<ProductModel> productModels) {
        validateRequest(productModels.size(), "product.id.notFound", Validators.greaterOrEqualTo(1));
        productModels.forEach(productModel -> productValidator.validate(productModel));
    }

    private void validateBuyer(Long buyerId) {
        validateRequest(buyerId.intValue(), "buyer.id.invalid", Validators.greaterOrEqualTo(1));
    }

    private void validateWareHouse(Long wareHouseId) {
        validateRequest(wareHouseId.intValue(), "warehouse.id.invalid", Validators.greaterOrEqualTo(1));
    }

    private void validateQuantity(int quantity) {
        validateRequest(quantity, "product.quantity.notValid", Validators.greaterOrEqualTo(1));
    }
}
