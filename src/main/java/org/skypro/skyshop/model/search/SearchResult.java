package org.skypro.skyshop.model.search;

import java.util.Objects;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    // Статический фабричный метод
    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(
                searchable.getId().toString(), // Преобразуем UUID в строку
                searchable.getName(),
                searchable.getContentType()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
