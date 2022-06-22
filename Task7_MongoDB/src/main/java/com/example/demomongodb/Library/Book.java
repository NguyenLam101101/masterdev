package com.example.demomongodb.Library;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "book")
public class Book {
    private String _id;
    private String bookName;
    private String author;
    private String publicationDate;
    private String describe;
}
