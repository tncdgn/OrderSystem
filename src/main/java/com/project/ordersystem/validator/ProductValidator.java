package com.project.ordersystem.validator;

import com.project.ordersystem.entity.Product;
import com.project.ordersystem.model.ProductModel;
import com.project.ordersystem.validator.util.Validators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductValidator extends BaseValidator<ProductModel> {

    @Override
    public void validate(ProductModel model) {
        validateProductModels(model);
    }

    public void validateStock(Product product, List<ProductModel> productModels) {
        productModels.forEach(productModel -> {
            if (productModel.getProductId().equals(product.getId())) {
                validateRequest(product.getStock(), "product.outOfStock", Validators.greaterOrEqualTo(productModel.getQuantity()));
            }
        });
    }

    private void validateProductModels(ProductModel productModel) {
        validateRequest(productModel.generateProductIdAsInt(), "product.id.notValid", Validators.greaterOrEqualTo(1));
        validateQuantity(productModel.getQuantity());
    }

    private void validateQuantity(int quantity) {
        validateRequest(quantity, "product.quantity.notValid", Validators.greaterOrEqualTo(1));
    }
}
