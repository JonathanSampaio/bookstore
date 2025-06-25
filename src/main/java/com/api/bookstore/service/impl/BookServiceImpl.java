package com.api.bookstore.service.impl;

import com.api.bookstore.handler.exception.ResourceNotFoundException;
import com.api.bookstore.model.Book;
import com.api.bookstore.model.dto.BookResponseDTO;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.BookService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("books")
    @Override
    public Page<BookResponseDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(Book::toDTO);
    }

    @Cacheable("book")
    @Override
    public BookResponseDTO getBookById(long id) {
        Book bookById = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return Book.toDTO(bookById);
    }

    @Cacheable("genreBooks")
    @Override
    public Page<BookResponseDTO> getBooksByGenre(String genre, Pageable pageable) {
        Page<BookResponseDTO> listBookByGenre = bookRepository.findByGenreIgnoreCase(genre, pageable)
                .map(Book::toDTO);
        if (listBookByGenre.isEmpty()) {
            throw new ResourceNotFoundException("No books found for genre: " + genre);
        }
        return listBookByGenre;
    }

    @Cacheable("authorBooks")
    @Override
    public Page<BookResponseDTO> getBooksByAuthor(String author, Pageable pageable) {
        Page<BookResponseDTO> listBookByAuthor = bookRepository.findByAuthorIgnoreCase(author, pageable)
                .map(Book::toDTO);
        if (listBookByAuthor.isEmpty()) {
            throw new ResourceNotFoundException("No books found for author: " + author);
        }
        return listBookByAuthor;
    }

}
