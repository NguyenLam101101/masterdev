package com.example.demomongodb.Controller;

import com.example.demomongodb.Library.Book;
import com.example.demomongodb.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService service;

    @GetMapping("/findById/{id}")
    public Optional<Book> getBookById(@PathVariable String id){
        return service.getBookById(id);
    }

    @GetMapping("/findBookByBookName/{bookName}")
    public Book getBookByBookName(@PathVariable String bookName){
        return service.getBookByBookNameContains(bookName);
    }

    @GetMapping("/findBookByAuthor/{author}")
    public Book getBookByAuthor(@PathVariable String author){
        return service.getBookByAuthorContains(author);
    }

    @GetMapping("/findBooks")
    public List<Book> getBooks(){
        return service.getBooks();
    }

    @PostMapping("/setBook")
    public String setBook(@RequestBody Book book){
        service.setBook(book);
        return "Insert %s successfully".formatted(book);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public String deleteBookById(@PathVariable String id){
        service.deleteBookById(id);
        return "Delete id_book: %s successfully".formatted(id);
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book){
        service.deleteBook(book);
        return "Delete %s successfully".formatted(book);
    }

    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable String id, @RequestBody Book new_book){
        service.updateBook(id, new_book);
        return "Update successfully";
    }
}
