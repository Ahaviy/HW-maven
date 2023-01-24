package org.example.comparators;

import org.example.University;

public class UniversityComparatorByYearOfFoundation implements IUniversityComparator {

    @Override
    public int compare(University o1, University o2) {
        return Integer.compare(o1.getYearOfFoundation(), o2.getYearOfFoundation());
    }


}
