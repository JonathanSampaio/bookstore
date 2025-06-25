package com.api.bookstore.controller;

import com.api.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void shouldGetBooks() {
        assertThatCode(() -> bookController.getBooks(0, 10, "*"))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldGetBooksOrderAuthor() {
        assertThatCode(() -> bookController.getBooks(0, 10, "author,asc"))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldGetBookById() {
        assertThatCode(() -> bookController.getBookById(1L))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldGetBooksByGenre() {
        assertThatCode(() -> bookController.getBooksByGenre("fiction",0, 10, "*"))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldGetBooksByAuthor() {
        assertThatCode(() -> bookController.getBooksByAuthor("Robert C. Martin",0, 10, "*"))
                .doesNotThrowAnyException();
    }
}