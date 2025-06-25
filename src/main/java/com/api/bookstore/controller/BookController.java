package com.api.bookstore.controller;

import com.api.bookstore.model.dto.BookResponseDTO;
import com.api.bookstore.service.BookService;
import com.api.bookstore.utils.PageUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> getBooks(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort
    ) {
        Pageable pageable = PageUtils.transformePagination(pageNumber, size, sort);

        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Page<BookResponseDTO>> getBooksByGenre(
            @PathVariable("genre") String genre,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort
                                                                 ) {
        Pageable pageable = PageUtils.transformePagination(pageNumber, size, sort);

        return ResponseEntity.ok(bookService.getBooksByGenre(genre, pageable));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Page<BookResponseDTO>> getBooksByAuthor(
            @PathVariable("author") String author,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {

        Pageable pageable = PageUtils.transformePagination(pageNumber, size, sort);

        return ResponseEntity.ok(bookService.getBooksByAuthor(author, pageable));
    }
}
