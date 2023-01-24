package org.example.comparators;

import org.example.Student;

public class StudentComparatorAvgExamScore implements IStudentComparator {

    @Override
    public int compare(Student o1, Student o2) {
        return Float.compare(o1.getAvgExamScore(),  o2.getAvgExamScore());
    }


}
