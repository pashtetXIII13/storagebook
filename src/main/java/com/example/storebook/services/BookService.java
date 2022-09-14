package com.example.storebook.services;


import com.example.storebook.dto.BookDto;

import java.util.List;

/**
 * @author pashtet
 */
public interface BookService {

    BookDto getBook(Long id);

    BookDto addBook(BookDto book);

    List<BookDto> getBooks();

    void deleteBook(Long id);

    void deleteBooks();
}
