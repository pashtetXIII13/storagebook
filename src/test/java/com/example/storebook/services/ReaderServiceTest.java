package com.example.storebook.services;

import com.example.storebook.dto.ReaderDto;
import com.example.storebook.dto.StorageDto;
import com.example.storebook.exception.ReaderNotFoundException;
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
class ReaderServiceTest {

    ReaderDto reader;
    StorageDto storageDto = StorageDto
            .builder()
            .name("Библиотека №1")
            .build();
    @Autowired
    ReaderService readerService;
    @Autowired
    StorageService storageService;

    @BeforeAll
    public void init() {
        storageDto = storageService.addStorage(storageDto);
    }

    @Test
    @Order(2)
    void getReaderById() throws ReaderNotFoundException {
        assertNotNull(readerService.getReader(reader.getId()));
    }

    @Test
    @Order(1)
    void addReader() {
        reader = readerService.addReader(ReaderDto
                .builder()
                .firstName("Иван")
                .lastName("Иванов")
                .storage(storageDto)
                .build());

        assertInstanceOf(ReaderDto.class, reader);
    }

    @Test
    @Order(3)
    void deleteReader() {
        readerService.deleteReader(reader.getId());
        assertThrows(ReaderNotFoundException.class, this::getReaderById);
    }

    @AfterAll
    void deInit() {
        storageService.deleteStorage(storageDto.getId());
    }
}