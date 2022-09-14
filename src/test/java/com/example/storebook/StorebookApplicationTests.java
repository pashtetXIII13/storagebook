package com.example.storebook;

import com.example.storebook.controller.BookController;
import com.example.storebook.controller.GenreController;
import com.example.storebook.controller.ReaderController;
import com.example.storebook.controller.StorageController;
import com.example.storebook.mapper.BookMapper;
import com.example.storebook.mapper.GenreMapper;
import com.example.storebook.mapper.ReaderMapper;
import com.example.storebook.mapper.StorageMapper;
import com.example.storebook.services.BookService;
import com.example.storebook.services.GenreService;
import com.example.storebook.services.ReaderService;
import com.example.storebook.services.StorageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class StorebookApplicationTests {

    @Autowired
    BookController bookController;
    @Autowired
    GenreController genreController;
    @Autowired
    ReaderController readerController;
    @Autowired
    StorageController storageController;

    @Autowired
    BookMapper bookMapper;
    @Autowired
    GenreMapper genreMapper;
    @Autowired
    ReaderMapper readerMapper;
    @Autowired
    StorageMapper storageMapper;

    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;
    @Autowired
    ReaderService readerService;
    @Autowired
    StorageService storageService;
    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
        assertThat(genreController).isNotNull();
        assertThat(readerController).isNotNull();
        assertThat(storageController).isNotNull();
        assertThat(bookMapper).isNotNull();
        assertThat(genreMapper).isNotNull();
        assertThat(readerMapper).isNotNull();
        assertThat(storageMapper).isNotNull();
        assertThat(bookService).isNotNull();
        assertThat(genreService).isNotNull();
        assertThat(readerService).isNotNull();
        assertThat(storageService).isNotNull();
    }

}
