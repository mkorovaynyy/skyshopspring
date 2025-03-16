package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Collection<Product> getAllProducts() {
        return storageOfProducts.values();
    }

    public Collection<Article> getAllArticles() {
        return storageOfArticles.values();
    }

    public Map<UUID, Product> getStorageOfProducts() {
        return storageOfProducts;
    }

    public Map<UUID, Article> getStorageOfArticles() {
        return storageOfArticles;
    }

    // Метод для возврата всех Searchable (продуктов и статей)
    public Collection<Searchable> getAllSearchables() {
        return Stream.concat(
                storageOfProducts.values().stream(),
                storageOfArticles.values().stream()
        ).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageOfProducts.get(id));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StorageService that = (StorageService) o;
        return Objects.equals(storageOfProducts, that.storageOfProducts) && Objects.equals(storageOfArticles, that.storageOfArticles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageOfProducts, storageOfArticles);
    }

    @Override
    public String toString() {
        return "StorageService{" +
                "storageOfProducts=" + storageOfProducts +
                ", storageOfArticles=" + storageOfArticles +
                '}';
    }
}
