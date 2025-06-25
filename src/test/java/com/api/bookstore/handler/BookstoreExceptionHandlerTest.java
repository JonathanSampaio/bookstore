package com.api.bookstore.handler;

import com.api.bookstore.handler.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class BookstoreExceptionHandlerTest {

    @InjectMocks
    private BookstoreExceptionHandler handler; // no usages

    @Test
    void shouldReturnsNotFoundWithMessageWhenResourceNotFoundExceptionIsThrown() {
        String mensagem = "Livro não encontrado";
        ResourceNotFoundException ex = new ResourceNotFoundException(mensagem);

        ResponseEntity<String> response =
                new BookstoreExceptionHandler().handleNotFound(ex);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals(mensagem, response.getBody());
    }

    @Test
    void shouldReturnsNotFoundWithEmptyMessageWhenResourceNotFoundExceptionWithoutMessage() {
        ResourceNotFoundException ex = new ResourceNotFoundException(null);

        ResponseEntity<String> response =
                new BookstoreExceptionHandler().handleNotFound(ex);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}