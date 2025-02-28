package org.skypro.skyshop.model.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable o1, Searchable o2) {
        int lengthComparison = Integer.compare(o2.getName().length(), o1.getName().length());
        if (lengthComparison != 0) {
            return lengthComparison;
        }
        return o1.getName().compareTo(o2.getName());
    }
}
