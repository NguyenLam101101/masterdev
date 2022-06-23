package com.example.demomongodb.Repository;

import com.example.demomongodb.Library.Book;
import org.springframework.data.mongodb.repository.MongoRepository;import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Object>{
    List<Book> findBooksByPublicationDateBetween(Date startDate, Date endDate);
    @Query(value = "db.book.find({$text: {$search: \"?1\"}})")
    List<Book> findFullText(String text);
}
