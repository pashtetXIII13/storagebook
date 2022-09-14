package com.example.storebook.mapper;

import com.example.storebook.dto.StorageDto;
import com.example.storebook.model.Reader;
import com.example.storebook.model.Storage;
import com.example.storebook.utils.mapper.CycleAvoidingMappingContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author pashtet
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class StorageMapperTest {
    StorageDto storageDto = StorageDto
            .builder()
            .name("Библиотека №1")
            .build();
    Reader reader = Reader
            .builder()
            .id(1L)
            .firstName("Иван")
            .lastName("Иванов")
            .build();
    List<Reader> readers = new ArrayList<>();
    Storage storage;
    @Autowired
    StorageMapper mapper;
    @Autowired
    CycleAvoidingMappingContext context;

    @Test
    void bookStorageToBookStorageDto() {
        readers.add(reader);
        storage = Storage
                .builder()
                .id(1L)
                .name("Библиотека №2")
                .readers(readers)
                .build();
        assertInstanceOf(Storage.class, mapper.toEntity(storageDto));
    }

    @Test
    void bookStorageDtoToBookStorage() {
        assertInstanceOf(StorageDto.class, mapper.toDto(storage, context));
    }
}