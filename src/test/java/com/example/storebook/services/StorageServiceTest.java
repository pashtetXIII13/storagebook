package com.example.storebook.services;

import com.example.storebook.dto.StorageDto;
import com.example.storebook.exception.StorageNotFoundException;
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
class StorageServiceTest {

    StorageDto storageDto = StorageDto
            .builder()
            .name("Библиотека №1")
            .build();
    @Autowired
    StorageService service;

    @Test
    @Order(2)
    void getBookStorage() {
        assertNotNull(service.getStorage(storageDto.getId()));
    }

    @Test
    @Order(1)
    void addBookStorage() {
        storageDto = service.addStorage(storageDto);
        assertInstanceOf(StorageDto.class, storageDto);
    }

    @Test
    @Order(3)
    void deleteBookStorage() {
        service.deleteStorage(storageDto.getId());
        assertThrows(StorageNotFoundException.class, this::getBookStorage);
    }

}