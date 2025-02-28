package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private Map<String, List<Product>> productsMap = new HashMap<>();

    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        productsMap.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
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
