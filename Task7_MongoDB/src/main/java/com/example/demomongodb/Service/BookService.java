package com.example.demomongodb.Service;

import com.example.demomongodb.Library.Book;
import com.example.demomongodb.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    public Optional<Book> getBookById(String id){
        return repository.findById(id);
    }

    public List<Book> getBookByText(String text){
        return repository.findFullText(text);
    }

    public List<Book> getBooksByPublicationDateBetween(Date startDate, Date endDate){
        return repository.findBooksByPublicationDateBetween(startDate, endDate);
    }

    public List<Book> getBooks(){
        return repository.findAll();
    }

    public void setBook(Book book){
        repository.save(book);
    }

    public void deleteBookById(String id){
        repository.deleteById(id);
    }

    public void deleteBook(Book book){
        repository.delete(book);
    }

    public void updateBook(String id, Book new_book){
        Book book = repository.findById(id).get();
        if (new_book.getBookName()!=null)
            book.setBookName(new_book.getBookName());
        if (new_book.getAuthor()!=null)
            book.setAuthor(new_book.getAuthor());
        if (new_book.getPublicationDate()!=null)
            book.setPublicationDate(new_book.getPublicationDate());
        if (new_book.getDescribe()!=null)
            book.setDescribe(new_book.getDescribe());
        repository.save(book);
    }
}
