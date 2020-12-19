package com.java.ds.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomComparator {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(new Student(1, "tamal", "science"),
                new Student(2, "jhilik", "science"),
                new Student(3, "epshita", "science"),
                new Student(4, "maa", "science"));

        // Type 1
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getRoll() < o2.getRoll()) {
                    return 1;
                } else if (o1.getRoll() > o2.getRoll()) {
                    return -1;
                }
                return 0;
            }
        });

        // Type 2
        studentList.sort((o1, o2) -> {
            if (o1.getRoll() < o2.getRoll()) {
                return 1;
            } else if (o1.getRoll() > o2.getRoll()) {
                return -1;
            }
            return 0;
        });

        // Type 3
        Comparator<Student> rollComparator = Comparator.comparing(Student::getRoll, (o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            }
            return 0;
        });
        Collections.sort(studentList, rollComparator);
        System.out.println(studentList);
        // or studentList.sort(rollComparator);
    }

    static class Student {
        private int roll;
        private String name;
        private String stream;

        public Student(int roll, String name, String stream) {
            this.roll = roll;
            this.name = name;
            this.stream = stream;
        }

        public int getRoll() {
            return roll;
        }

        public String getName() {
            return name;
        }

        public String getStream() {
            return stream;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "roll=" + roll +
                    ", name='" + name + '\'' +
                    ", stream='" + stream + '\'' +
                    '}';
        }
    }
}