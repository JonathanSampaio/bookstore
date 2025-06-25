package com.api.bookstore.service;

import com.api.bookstore.model.dto.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<BookResponseDTO> getAllBooks(Pageable pageable);

    BookResponseDTO getBookById(long id);

    Page<BookResponseDTO> getBooksByGenre(String genre, Pageable pageable);

    Page<BookResponseDTO> getBooksByAuthor(String author, Pageable pageable);
}
