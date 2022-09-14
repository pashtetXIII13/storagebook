package com.example.storebook.services;

import com.example.storebook.dto.BookDto;
import com.example.storebook.exception.BookNotFoundException;
import com.example.storebook.exception.StorageNotFoundException;
import com.example.storebook.mapper.BookMapper;
import com.example.storebook.model.Book;
import com.example.storebook.repository.BookRepository;
import com.example.storebook.utils.mapper.CycleAvoidingMappingContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pashtet
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookServiceImpl implements BookService {

    private static final String NOT_FOUND_ID = "Book not found: id = ";
    BookRepository repository;
    BookMapper mapper;

    CycleAvoidingMappingContext context;

    @Override
    public BookDto getBook(Long id) {
        return mapper.toDto(repository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(NOT_FOUND_ID + id)), context);
    }

    @Override
    public List<BookDto> getBooks() {
        Iterable<Book> books = repository.findAll();
        List<BookDto> booksDto = new ArrayList<>();
        books.forEach(book -> booksDto.add(mapper.toDto(book, context)));
        return booksDto;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        return mapper.toDto(repository.save(mapper.toEntity(bookDto)), context);
    }

    @Override
    public void deleteBook(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn(e.getMessage());
            throw new StorageNotFoundException(NOT_FOUND_ID + id);
        }
    }

    @Override
    public void deleteBooks() {
        repository.deleteAll();
    }
}
