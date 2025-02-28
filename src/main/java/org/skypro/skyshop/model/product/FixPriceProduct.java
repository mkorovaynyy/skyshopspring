package org.skypro.skyshop.model.product;

public class FixPriceProduct extends Product {

    private static final Integer FIXED_PRICE = 50; // Фиксированная цена

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public Integer getPrice() {
        return FIXED_PRICE;
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
