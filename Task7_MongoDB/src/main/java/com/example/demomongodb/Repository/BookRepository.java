package com.example.demomongodb.Repository;

import com.example.demomongodb.Library.Book;
import org.springframework.data.mongodb.repository.MongoRepository;import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Object>{
    Book findBookByAuthorContains(String author);
    Book findBookByBookNameContains(String bookName);
}
