package com.java.ds.comparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        List<Integer> finalGrades = new ArrayList<>();
        finalGrades = grades.stream().map(each -> {
            if (each % 5 <= 3 || each < 38) {
                return each;
            } else {
                return (each + (5 - each % 5));
            }
        }).collect(Collectors.toList());
        return finalGrades;
    }

}
