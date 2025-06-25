package com.api.bookstore.model.dto;

import com.api.bookstore.model.Book;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookResponseDTO {

    private long id;
    private String author;
    private String title;
    private String genre;
    private String publisher;
    private String isbn;

    public BookResponseDTO(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.genre = book.getGenre();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
    }
}
