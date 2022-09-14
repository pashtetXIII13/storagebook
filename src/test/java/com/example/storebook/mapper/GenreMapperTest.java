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

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author pashtet
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class GenreMapperTest {
    Storage storage;
    StorageDto storageDto;
    Reader reader;
    ReaderDto readerDto;
    Book book;
    BookDto bookDto;
    GenreDto genreDto;
    Set<Storage> storages = new HashSet<>();
    Set<StorageDto> storagesDto = new HashSet<>();
    Set<Reader> readers = new HashSet<>();
    Set<ReaderDto> readersDto = new HashSet<>();
    Set<Book> books = new HashSet<>();
    Set<BookDto> booksDto = new HashSet<>();
    Genre genre;

    @Autowired
    GenreMapper mapper;
    @Autowired
    CycleAvoidingMappingContext context;

    @BeforeAll
    void init() {
        storage = Storage
                .builder()
                .name("Библиотека №1")
                .build();

        storages.add(storage);

        storageDto = StorageDto
                .builder()
                .name("Библиотека №1")
                .build();

        storagesDto.add(storageDto);
        reader = Reader
                .builder()
                .firstName("Иван")
                .lastName("Иванов")
                .storage(storage)
                .build();

        readers.add(reader);
        readerDto = ReaderDto
                .builder()
                .firstName("Иван")
                .lastName("Иванов")
                .storage(storageDto)
                .build();

        readersDto.add(readerDto);

        book = Book
                .builder()
                .title("Война миров")
                .author("Г. Уэльс")
                .storages(storages)
                .readers(readers)
                .build();

        books.add(book);

        bookDto = BookDto
                .builder()
                .title("Война миров")
                .author("Г. Уэльс")
                .storages(storagesDto)
                .readers(readersDto)
                .genre(genreDto)
                .build();

        booksDto.add(bookDto);
        genre = Genre
                .builder()
                .id(1L)
                .genreName("Фантастика")
                .books(books)
                .build();

        genreDto = GenreDto
                .builder()
                .id(1L)
                .genreName("Фантастика")
                .books(booksDto)
                .build();
    }

    @Test
    void genreDtoToGenre() {
        assertInstanceOf(Genre.class, mapper.toEntity(genreDto));
    }

    @Test
    void genreToGenreDto() {
        System.out.println(genre);
        assertInstanceOf(GenreDto.class, mapper.toDto(genre, context));
    }
}