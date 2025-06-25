package com.api.bookstore.model;

import com.api.bookstore.model.dto.BookResponseDTO;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn")
    private String isbn;

    // Método para converter para DTO
    public static BookResponseDTO toDTO(Book book) {
        return new BookResponseDTO(book);
    }

    /**
     * Gera um objeto Book com dados aleatórios usando a biblioteca Faker.
     * @return Um objeto Book populado com dados aleatórios.
     */
    public static Book fromBookFaker() {
        Faker faker = new Faker(new Locale("pt-BR"));
        Book book = new Book();
        book.setTitle(faker.book().title());
        book.setGenre(faker.book().genre());
        book.setAuthor(faker.book().author());
        book.setPublisher(faker.book().publisher());
        book.setIsbn(faker.number().digits(32));
        return book;
    }
}