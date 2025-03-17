package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    // Конструктор с dependency injection
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);

        if (productOptional.isEmpty()) {
            throw new NoSuchProductException("Товар с ID " + id + " не найден.");
        }

        productBasket.addProduct(id);
    }

    //Метод для получения корзины пользователя.

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productsInBasket = productBasket.getPRODUCTSMAPUUID();

        List<BasketItem> basketItems = productsInBasket.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new NoSuchProductException("Товар с ID " + productId + " не найден."));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}
