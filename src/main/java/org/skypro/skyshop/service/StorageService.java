package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> storageOfProducts;
    private final Map<UUID, Article> storageOfArticles;


    public StorageService() {
        this.storageOfProducts = new HashMap<>();
        this.storageOfArticles = new HashMap<>();
        forTest();
    }

    //приватный метод для заполнения мэп тестовыми данными
    private void forTest() {
        Product apple = new SimpleProduct("Яблоко", 50, UUID.randomUUID());
        Product bread = new SimpleProduct("Хлеб", 30, UUID.randomUUID());
        Product milk = new DiscountedProduct("Молоко", 80, 10, UUID.randomUUID());
        Product cheese = new FixPriceProduct("Сыр", UUID.randomUUID());
        Product juice = new DiscountedProduct("Сок", 100, 20, UUID.randomUUID());
        Article article1 = new Article("Как выбрать яблоки", "Советы по выбору свежих яблок.", UUID.randomUUID());
        Article article2 = new Article("Польза молока", "Почему молоко полезно для здоровья.", UUID.randomUUID());
        storageOfProducts.put(apple.getId(), apple);
        storageOfProducts.put(bread.getId(), bread);
        storageOfProducts.put(milk.getId(), milk);
        storageOfProducts.put(cheese.getId(), cheese);
        storageOfProducts.put(juice.getId(), juice);
        storageOfArticles.put(article1.getId(), article1);
        storageOfArticles.put(article2.getId(), article2);
    }
}
