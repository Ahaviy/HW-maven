package org.example.comparators;

public class Comparators {

    public static IStudentComparator getStudentComparator(CompareStudent compareStudent) {
        return switch (compareStudent) {
            case byCurrentCourseNumber -> new StudentComparatorByCurrentCourseNumber();
            case byFullName -> new StudentComparatorByFullName();
            case byAvgExamScore -> new StudentComparatorAvgExamScore();
            case UniversityId -> new StudentComparatorByUniversityID();
        };
    }

    public static IUniversityComparator getUniversityComporator(CompareUniversity compareUniversity) {
        return switch (compareUniversity){
            case byFullName -> new UniversityComparatorByFullName();
            case byId -> new UniversityComparatorById();
            case byShortName -> new UniversityComparatorByShortName();
            case byStudyProfile -> new UniversityComparatorByStudyProfile();
            case byYearOfFoundationl -> new UniversityComparatorByYearOfFoundation();
        };
    }


}
