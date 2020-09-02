package com.project.ordersystem.service;

import com.project.ordersystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class DataLoaderService {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WareHouseAddressService wareHouseAddressService;

    @Autowired
    private MessageResourceService messageResourceService;

    @PostConstruct
    public void init() {
        prepareData();
    }

    public void prepareData() {
        Buyer migros = Buyer.builder().companyName("Migros").email("migros@migros.com").id(1l).build();
        Buyer carrefour = Buyer.builder().companyName("Carrefour").email("carrefour@carrefour.com").id(2l).build();

        buyerService.saveBulk(Arrays.asList(migros, carrefour));

        Category foodAndDrink = Category.builder().title("Food and Drink").build();
        categoryService.save(foodAndDrink);

        Category drink = Category.builder().title("Drink").parent(foodAndDrink).build();
        Category food = Category.builder().title("Food").parent(foodAndDrink).build();

        categoryService.saveBulk(Arrays.asList(food, drink));

        Seller cocaColaCompany = Seller.builder().companyName("Coca Cola").email("cola@cola.com").build();
        Seller pinarCompany = Seller.builder().companyName("Pinar AŞ").email("pinar@pinar.com").build();

        sellerService.saveBulk(Arrays.asList(cocaColaCompany, pinarCompany));

        Product cocaCola = Product.builder()
                .category(drink).preparationTime(2)
                .price(BigDecimal.TEN).seller(cocaColaCompany).stock(250)
                .title("Coca Cola")
                .build();

        Product milk = Product.builder().category(drink).preparationTime(3)
                .price(BigDecimal.valueOf(5)).seller(pinarCompany).stock(100)
                .title("Milk")
                .build();

        productService.saveBulk(Arrays.asList(cocaCola, milk));

        WareHouseAddress migrosAddress = WareHouseAddress.builder().address("Ataturk mahallesi Ataşehir").city("Istanbul").buyer(migros).postCode("34750").build();
        WareHouseAddress pinarAddress = WareHouseAddress.builder().address("Asma mahallesi Kadıköy").city("Istanbul").buyer(carrefour).postCode("34734").build();

        wareHouseAddressService.saveBulk(Arrays.asList(migrosAddress, pinarAddress));

        MessageResource productIdNotValid = MessageResource.builder().key("product.id.notValid").content("Geçersiz ürün id").build();
        MessageResource productNotFound = MessageResource.builder().key("product.notFound").content("Ürün Bulunamadı").build();
        MessageResource productQuantityNotValid = MessageResource.builder().key("product.quantity.notValid").content("Girilen ürün miktarı geçersiz").build();
        MessageResource productIdNotFound = MessageResource.builder().key("product.id.notFound").content("Siparişinizde en az bir ürün bulunmalı.").build();
        MessageResource productOutOfStock = MessageResource.builder().key("product.outOfStock").content("Üründen girilen miktarda bulunmamaktadır.").build();
        MessageResource buyerIdInvalid = MessageResource.builder().key("buyer.id.invalid").content("Geçersiz müşteri id.").build();
        MessageResource buyerNotFound = MessageResource.builder().key("buyer.notFound").content("Müşteri Bulunamadı.").build();
        MessageResource warehouseIdInvalid = MessageResource.builder().key("warehouse.id.invalid").content("Geçersiz depo adres id").build();
        MessageResource warehouseNotFound = MessageResource.builder().key("warehouse.notFound").content("Depo adresi bulunamadı.").build();

        messageResourceService.saveBulk(Arrays.asList
                (
                        productIdNotValid, productNotFound, productQuantityNotValid, productIdNotFound, productOutOfStock,
                        buyerIdInvalid ,buyerNotFound,warehouseIdInvalid,warehouseNotFound
                )
        );

        messageResourceService.init();
    }
}
