package org.skypro.skyshop.model.search;

import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SearchEngine {
    private Set<Searchable> searchables = new HashSet<>();

    // Метод добавления объекта в поисковый движок
    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    // Метод поиска всех подходящих результатов
    public Set<Searchable> search(String query) {
        return searchables.stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchableComparator())));
    }

    // Метод поиска самого подходящего элемента
    public Searchable findBestMatch(String query) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable searchable : searchables) {
            String searchTerm = searchable.getSearchTerm().toLowerCase();
            int count = countOccurrences(searchTerm, query.toLowerCase());
            if (count > maxCount) {
                maxCount = count;
                bestMatch = searchable;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound("Не найдено подходящего результата для запроса: " + query);
        }

        return bestMatch;
    }

    private int countOccurrences(String str, String substring) {
        int count = 0;
        int index = 0;
        int substringIndex = str.indexOf(substring, index);

        while (substringIndex != -1) {
            count++;
            index = substringIndex + substring.length();
            substringIndex = str.indexOf(substring, index);
        }

        return count;
    }
}
