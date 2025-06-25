package com.api.bookstore.utils;

import com.api.bookstore.model.Book;
import com.api.bookstore.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BookUtils {

    private final BookRepository bookRepository;

    public BookUtils(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void initializeBooks() {
        for (int i = 0; i < 100; i++){
            bookRepository.save(Book.fromBookFaker());
        }
    }
}
