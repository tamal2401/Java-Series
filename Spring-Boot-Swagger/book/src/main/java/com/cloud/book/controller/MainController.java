package com.cloud.book.controller;

import com.cloud.book.model.Book;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Api(tags = "Book controller for intercepting requests")
@RestController
public class MainController {

    public final static List<Book> allBooks = new ArrayList<>();

    static{
        allBooks.add(new Book(1, "Chader Pahar", "Bibhutibhuson bandopadhya", 1000.50, "Adventure"));
        allBooks.add(new Book(2, "Feludar Punch", "Satyajit Ray", 550, "Detective"));
        allBooks.add(new Book(3, "Koni", "Moti Nandi", 110.80, "Sports"));
        allBooks.add(new Book(4, "SOjarur Kata", "Saradindu Bandopadhya", 780.90, "Detective"));
    }

    @PostMapping("/api/add")
    public void addBook(@RequestBody Book book){
        allBooks.add(book);
    }

    @GetMapping("/api/get/all")
    public List<Book> getAllBooks(){
        return allBooks;
    }

    @DeleteMapping("/api/delete/{bookName}")
    public void deleteBook(@PathVariable("bookName") String bookName){

        allBooks.removeIf(book -> book.getBookName().contentEquals(bookName.trim()));
    }

    @GetMapping("/api/get")
    public Book getBookById(@RequestParam("id") long bookId){
        Iterator<Book> itr = allBooks.iterator();
        while(itr.hasNext()){
            Book book = itr.next();
            if(bookId == book.getId()) {
                return book;
            }
        }
        return new Book();
    }

    @GetMapping("/api/print")
    public void getBookById(@RequestParam Map<String, String> identifiers){
        System.out.println("debug");
        identifiers.forEach((key, value) -> System.out.printf("key:%s , value: %s%n", key, value));
    }
}
