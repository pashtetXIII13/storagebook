package com.example.storebook.mapper;

import com.example.storebook.dto.BookDto;
import com.example.storebook.dto.GenreDto;
import com.example.storebook.dto.ReaderDto;
import com.example.storebook.dto.StorageDto;
import com.example.storebook.model.Book;
import com.example.storebook.model.Genre;
import com.example.storebook.model.Reader;
import com.example.storebook.model.Storage;
import com.example.storebook.utils.mapper.CycleAvoidingMappingContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author pashtet
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class BookMapperTest {
    StorageDto storageDto;
    Storage storage;
    Book book;
    BookDto bookDto;
    Genre genre;
    GenreDto genreDto;
    Set<StorageDto> storagesDto = new HashSet<>();
    Set<Reader> readers = new HashSet<>();
    Set<ReaderDto> readersDto = new HashSet<>();
    Set<Storage> storages = new HashSet<>();

    @Autowired
    BookMapper mapper;
    @Autowired
    CycleAvoidingMappingContext context;

    @BeforeAll
    void init() {
        storageDto = StorageDto
                .builder()
                .id(1L)
                .name("Библиотека №1")
                .build();
        storagesDto.add(storageDto);

        storage = Storage
                .builder()
                .id(1L)
                .name("Библиотека №2")
                .build();
        storages.add(storage);

        genreDto = GenreDto
                .builder()
                .id(1L)
                .genreName("Хоррор")
                .build();

        genre = Genre
                .builder()
                .id(1L)
                .genreName("Наука")
                .build();

        book = Book
                .builder()
                .id(1L)
                .title("Сказки")
                .author("Братья Гримм")
                .genre(genre)
                .storages(storages)
                .readers(readers)
                .build();

        bookDto = BookDto
                .builder()
                .id(1L)
                .title("Вий")
                .author("Н.В. Гоголь")
                .genre(genreDto)
                .storages(storagesDto)
                .readers(readersDto)
                .build();
    }

    @Test
    void bookToBookDto() {
        BookDto bookDto1 = mapper.toDto(book, context);
        assertEquals(book.getId(), bookDto1.getId());
        assertEquals(book.getTitle(), bookDto1.getTitle());
        assertEquals(book.getAuthor(), bookDto1.getAuthor());
        assertEquals(book.getGenre().getGenreName(), bookDto1.getGenre().getGenreName());

    }

    @Test
    void bookDtoToBook() {
        assertInstanceOf(Book.class, mapper.toEntity(bookDto));
    }
}