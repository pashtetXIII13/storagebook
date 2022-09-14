package com.example.storebook.services;

import com.example.storebook.dto.BookDto;
import com.example.storebook.dto.GenreDto;
import com.example.storebook.dto.ReaderDto;
import com.example.storebook.dto.StorageDto;
import com.example.storebook.exception.BookNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pashtet
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class BookServiceTest {
    final Set<StorageDto> storagesDto = new HashSet<>();
    final Set<ReaderDto> readersDto = new HashSet<>();
    GenreDto genreDto;
    StorageDto storageDto;
    BookDto bookDto;
    ReaderDto readerDto;
    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;
    @Autowired
    StorageService storageService;

    @BeforeAll
    public void init() {
        genreDto = genreService.addGenre(GenreDto
                .builder()
                .genreName("Наука")
                .build());

        storageDto = storageService.addStorage(StorageDto
                .builder()
                .name("Библиотека №1")
                .build());

        readerDto = readerService.addReader(ReaderDto
                .builder()
                .firstName("Иван")
                .lastName("Иванов")
                .storage(storageDto)
                .build());

        bookDto = BookDto
                .builder()
                .title("Java для чайников")
                .author("И. И. Неизвестный")
                .storages(storagesDto)
                .genre(genreDto)
                .readers(readersDto)
                .build();
    }

    @Test
    @Order(2)
    @Transactional
    void getBookById() {
        assertNotNull(bookService.getBook(bookDto.getId()));
    }

    @Test
    @Order(1)
    void addBook() {
        storagesDto.add(storageDto);
        readersDto.add(readerDto);
        bookDto = bookService.addBook(bookDto);
        assertInstanceOf(BookDto.class, bookDto);
    }

    @Test
    @Order(3)
    void deleteBook() {
        bookService.deleteBook(bookDto.getId());
        assertThrows(BookNotFoundException.class, this::getBookById);
    }

    @AfterAll
    void deInit() {
        storageService.deleteStorage(storageDto.getId());
        genreService.deleteGenre(genreDto.getId());
    }
}