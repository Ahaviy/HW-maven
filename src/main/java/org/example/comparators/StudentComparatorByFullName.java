package org.example.comparators;

import org.apache.commons.lang3.StringUtils;
import org.example.Student;

public class StudentComparatorByFullName implements IStudentComparator {

    @Override
    public int compare(Student o1, Student o2) {
        return StringUtils.compare(o1.getFullName(), o2.getFullName());
    }


}
