package com.api.bookstore.repository;

import com.api.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByAuthorIgnoreCase(String author, Pageable pageable);

    Page<Book> findByGenreIgnoreCase(String genre, Pageable pageable);
}
