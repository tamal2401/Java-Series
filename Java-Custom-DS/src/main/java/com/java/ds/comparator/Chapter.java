package com.java.ds.comparator;

public class Chapter {
    private int chapterId;
    private String chapterName;

    public Chapter(int chapterId, String chapterName) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

//    @Override
//    public String toString() {
//        return "Chapter{" +
//                "chapterId=" + chapterId +
//                ", chapterName='" + chapterName + '\'' +
//                '}';
//    }
}
