package com.java.ds.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Book {
    private final int bookId;
    private final String bookName;
    private final List<Chapter> chapterList;

    public Book(int bookId, String bookName, List<Chapter> chapterList) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.chapterList = chapterList;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public List<Chapter> getChapterList() {
        return new ArrayList<>(chapterList);
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "bookId=" + bookId +
//                ", bookName='" + bookName + '\'' +
//                ", chapterList=" + chapterList +
//                '}';
//    }
}

