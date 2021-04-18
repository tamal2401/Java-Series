package com.cloud.book.controller;

import com.cloud.book.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class MainController {

    public final static List<Book> allBooks = new ArrayList<>();

    static{
        allBooks.add(new Book(1, "Chader Pahar", "Bibhutibhuson bandopadhya", 1000.50, "Adventure"));
        allBooks.add(new Book(2, "Feludar Punch", "Satyajit Ray", 550, "Detective"));
        allBooks.add(new Book(3, "Koni", "Moti Nandi", 110.80, "Sports"));
        allBooks.add(new Book(4, "SOjarur Kata", "Saradindu Bandopadhya", 780.90, "Detective"));
    }

    @PutMapping("/book/add")
    public void addBook(@RequestBody Book book){
        this.allBooks.add(book);
    }

    @GetMapping("/book/get/all")
    public List<Book> getAllBooks(){
        return this.allBooks;
    }

    @DeleteMapping("/book/delete/{bookName}")
    public void deleteBook(@PathVariable("bookName") String bookName){
        Iterator<Book> itr = this.allBooks.iterator();

        while(itr.hasNext()){
            if(itr.next().getBookName().contentEquals(bookName.trim())) itr.remove();
        }
    }

    @GetMapping("/book/get")
    public Book getBookById(@RequestParam("id") long bookId){
        Iterator<Book> itr = this.allBooks.iterator();
        while(itr.hasNext()){
            if(bookId == itr.next().getId()) {
                return itr.next();
            }
        }
        return new Book();
    }
}
