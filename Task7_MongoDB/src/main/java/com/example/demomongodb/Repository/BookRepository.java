package com.example.demomongodb.Repository;

import com.example.demomongodb.Library.Book;
import org.springframework.data.mongodb.repository.MongoRepository;import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Object>{
    List<Book> findBooksByAuthorContains(String author);
    List<Book> findBooksByBookNameContains(String bookName);
    List<Book> findBooksByAuthorContainsAndBookNameContains(String author, String bookName);
    List<Book> findBooksByPublicationDateBetween(Date startDate, Date endDate);
}
