package com.java.ds.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ABC {

    public static void main(String[] args) {
        List<Chapter> chapter = new ArrayList<>();
        chapter.add(new Chapter(1,"test"));
        chapter.add(new Chapter(2, "test2"));
        Book book = new Book(1, "Feluda", chapter);
        System.out.println(book.getChapterList());
        List<Chapter> chapters = book.getChapterList();
        chapters.add(new Chapter(3, "test3"));
        System.out.println(book.getChapterList());
    }
}

