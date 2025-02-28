package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final Integer FIXED_PRICE = 50; // Фиксированная цена
    private final UUID id;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);
        this.id = id;
    }

    @Override
    public Integer getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isSpecial() {
        return true; // Товар с фиксированной ценой является специальным
    }


    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
