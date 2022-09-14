package com.example.storebook.controller;

import com.example.storebook.dto.BookDto;
import com.example.storebook.services.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pashtet
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService service;

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookDto getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks() {
        return service.getBooks();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return service.addBook(bookDto);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

    @DeleteMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooks() {
        service.deleteBooks();
    }
}
