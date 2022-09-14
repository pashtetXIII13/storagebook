package com.example.storebook.services;

import com.example.storebook.dto.GenreDto;
import com.example.storebook.exception.GenreNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pashtet
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class GenreServiceTest {

    @Autowired
    GenreService genreService;

    GenreDto genreDto;

    @Test
    @Order(1)
    void addGenre() {
        genreDto = genreService.addGenre(GenreDto
                .builder()
                .genreName("Наука и техника")
                .build());
        assertInstanceOf(GenreDto.class, genreDto);
    }

    @Test
    @Order(2)
    void getGenreById() throws GenreNotFoundException {
        assertNotNull(genreService.getGenre(genreDto.getId()));
    }

    @Test
    @Order(3)
    void deleteGenre() {
        genreService.deleteGenre(genreDto.getId());
        assertThrows(GenreNotFoundException.class, this::getGenreById);
    }
}