package com.api.bookstore.service.impl;

import com.api.bookstore.handler.exception.ResourceNotFoundException;
import com.api.bookstore.model.Book;
import com.api.bookstore.model.dto.BookResponseDTO;
import com.api.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setup() {
        book = mock(Book.class);
    }

    @Test
    void shouldReturnsListOfAllBooksWhenBooksExist() {
        Book book2 = mock(Book.class);
        Pageable pageable = Page.empty().getPageable();

        when(bookRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(asList(book, book2), pageable, 2));

        Page<BookResponseDTO> result = bookServiceImpl.getAllBooks(pageable);

        assertEquals(2, result.getSize());
    }

    @Test
    void shouldReturnsEmptyListWhenNoBooksExist() {
        Pageable pageable = Page.empty().getPageable();

        when(bookRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<BookResponseDTO> result = bookServiceImpl.getAllBooks(pageable);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnsListOfBooksByExistingId() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponseDTO resultById = bookServiceImpl.getBookById(1L);

        assertNotNull(resultById);
    }

    @Test
    void shouldThrowsResourceNotFoundExceptionWhenNotFoundingBooksById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookServiceImpl.getBookById(1L)
        );
    }

    @Test
    void shouldReturnsListOfBooksByExistingGenre() {
        Pageable pageable = Page.empty().getPageable();

        when(bookRepository.findByGenreIgnoreCase("action", pageable)).thenReturn(new PageImpl<>(Collections.singletonList(book), pageable, 1));

        Page<BookResponseDTO> result = bookServiceImpl.getBooksByGenre("action", pageable);

        assertEquals(1, result.getSize());
    }

    @Test
    void shouldThrowsResourceNotFoundExceptionWhenNotFoundingBooksByGenre() {
        Pageable pageable = Page.empty().getPageable();

        when(bookRepository.findByGenreIgnoreCase("ABC", pageable)).thenReturn(Page.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookServiceImpl.getBooksByGenre("ABC", pageable)
        );
    }

    @Test
    void shouldReturnsListOfBooksByExistingAuthor() {
        Pageable pageable = Page.empty().getPageable();

        when(bookRepository.findByAuthorIgnoreCase("Autor", pageable)).thenReturn(new PageImpl<>(Collections.singletonList(book), pageable, 1));

        Page<BookResponseDTO> result = bookServiceImpl.getBooksByAuthor("Autor", pageable);

        assertEquals(1, result.getSize());
    }

    @Test
    void shouldThrowsResourceNotFoundExceptionWhenNotFoundingBooksByAuthor() {
        Pageable pageable = Page.empty().getPageable();

        when(bookRepository.findByAuthorIgnoreCase("Desconhecido", pageable)).thenReturn(Page.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookServiceImpl.getBooksByAuthor("Desconhecido", pageable)
        );
    }
}