package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    // Метод для получения термина поиска
    String getSearchTerm();

    // Метод для получения типа контента
    String getContentType();

    // Метод для получения имени объекта
    String getName();

    // Метод для преобразования объекта в строку
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }

    // Метод для получения id
    UUID getId();
}
