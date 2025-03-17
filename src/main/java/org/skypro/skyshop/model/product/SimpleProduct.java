package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {
    private Integer price;
    private final UUID id;

    public SimpleProduct(String name, Integer price, UUID id) {

        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть строго больше 0.");
        }
        this.price = price;
        this.id = id;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isSpecial() {
        return false; // Обычный товар не является специальным
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct that = (SimpleProduct) o;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }

    @Override
    public String toString() {
        return "SimpleProduct{" +
                "name=" + this.getName() +
                "price=" + price +
                '}';
    }
}
