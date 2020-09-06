package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.Order;
import com.project.ordersystem.entity.OrderItem;
import com.project.ordersystem.entity.Product;
import com.project.ordersystem.entity.ProductOrderItem;
import com.project.ordersystem.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.ordersystem.entity.generator.EntityGeneratorType.PRODUCT_ORDER_ITEM;

@Component
public class ProductOrderItemGenerator extends EntityGenerator<ProductOrderItem> {

    @Autowired
    private OrderGenerator orderGenerator;

    @Override
    public ProductOrderItem generate(EntityGeneratorModel model) {
        Product product = model.getProduct();
        ProductOrderItem productOrderItem = new ProductOrderItem();
        productOrderItem.setPreparationTime(product.getPreparationTime());
        productOrderItem.setPrice(generateTotalCost(product, model.getProductModels()));
        productOrderItem.setProduct(product);
        productOrderItem.setProductTitle(product.getTitle());
        productOrderItem.setSeller(product.getSeller());
        productOrderItem.setOrder(model.getOrder());
        productOrderItem.setQuantity(generateQuantity(product, model.getProductModels()));

        return productOrderItem;
    }

    @Override
    public EntityGeneratorType type() {
        return PRODUCT_ORDER_ITEM;
    }

    public List<OrderItem> generateProductOrderItem(EntityGeneratorModel entityGeneratorModel, List<Product> products) {
        List<OrderItem> productOrderItems = new ArrayList<>();
        Order order = orderGenerator.generate(entityGeneratorModel);

        for (Product product : products) {
            entityGeneratorModel.setProduct(product);
            entityGeneratorModel.setOrder(order);
            ProductOrderItem productOrderItem = generate(entityGeneratorModel);
            orderGenerator.addProductPriceToTotalCost(order, productOrderItem.getPrice());

            productOrderItems.add(productOrderItem);
        }

        return productOrderItems;
    }

    private BigDecimal generateTotalCost(Product product, List<ProductModel> productModels) {
        int quantity = generateQuantity(product, productModels);
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    private int generateQuantity(Product product, List<ProductModel> productModels) {
        return Math.toIntExact(productModels.stream().filter(productModel -> product.getId().equals(productModel.getProductId())).map(ProductModel::getQuantity).collect(Collectors.toList()).get(0));
    }
}
