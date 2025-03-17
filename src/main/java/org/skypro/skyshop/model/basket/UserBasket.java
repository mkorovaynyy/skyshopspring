package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public final class UserBasket {
    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
