package com.example.demomongodb.Library;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@ToString
@Document(collection = "book")
public class Book {
    @Id
    private String _id;
    @Field(name = "bookName")
    @TextIndexed
    private String bookName;
    @Field(name = "author")
    @TextIndexed
    private String author;
    @Field(name = "publicationDate")
    private Date publicationDate;
    @Field(name = "describe")
    private String describe;
}
