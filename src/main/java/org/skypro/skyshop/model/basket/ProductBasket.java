package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Service
@SessionScope
public class ProductBasket {
    private Map<String, List<Product>> productsMap = new HashMap<>();
    private final Map<UUID, Integer> PRODUCTSMAPUUID = new HashMap<>();


    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        productsMap.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    // Метод добавления продукта в корзину из нового ДЗ по спрингу, старые методы пока не удалял,
    // не ясно из текста дз оставлять их или удалить
    public void addProduct(UUID id) {
        PRODUCTSMAPUUID.put(id, PRODUCTSMAPUUID.getOrDefault(id, 0) + 1);
    }

    //Метод получения всех продуктов, которые сейчас есть в корзине
    public Map<UUID, Integer> getPRODUCTSMAPUUID() {
        return Collections.unmodifiableMap(PRODUCTSMAPUUID);
    }

    // Метод получения общей стоимости корзины
    public int getTotalCost() {
        return productsMap.values().stream().flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }


    // Метод печати содержимого корзины
    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = productsMap.values().stream()
                .flatMap(List::stream)
                .peek(System.out::println)
                .filter(Product::isSpecial)
                .mapToInt(product -> 1)
                .sum();
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    // Метод проверки наличия продукта в корзине по имени
    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }

    // Метод очистки корзины
    public void clearBasket() {
        productsMap.clear();
    }

    // Метод удаления продукта по имени
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = productsMap.remove(name);
        return removedProducts != null ? removedProducts : new ArrayList<>();
    }
}
