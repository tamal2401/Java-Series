package com.cloud.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;
    private String bookName;
    private String authorName;
    private double price;
    private String genre;

}
