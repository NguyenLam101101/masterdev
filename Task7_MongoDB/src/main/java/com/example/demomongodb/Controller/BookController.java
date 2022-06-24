package com.example.demomongodb.Controller;

import com.example.demomongodb.Library.Book;
import com.example.demomongodb.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/findByText/{text}")
    public List<Book> getByText(@PathVariable String text){
        return service.getBookByText(text);
    }

    @GetMapping("/findByPublicationDate")
    public List<Book> getByPublicationDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
        return service.getBooksByPublicationDateBetween(startDate, endDate);
    }

    @GetMapping("/findAll")
    public Page<Book> getBooks(@RequestParam int page){
        return service.getBooks(page);
    }

    @PostMapping("/set")
    public String setBook(@RequestBody Book book){
        service.setBook(book);
        return "Insert %s successfully".formatted(book);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteBookById(@PathVariable String id){
        service.deleteBookById(id);
        return "Delete id_book: %s successfully".formatted(id);
    }

    @DeleteMapping("/delete")
    public String deleteBook(@RequestBody Book book){
        service.deleteBook(book);
        return "Delete %s successfully".formatted(book);
    }

    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable String id, @RequestBody Book new_book){
        service.updateBook(id, new_book);
        return "Update successfully";
    }
}
