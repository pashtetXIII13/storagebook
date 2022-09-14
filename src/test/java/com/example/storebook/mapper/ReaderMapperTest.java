package com.example.storebook.mapper;

import com.example.storebook.dto.ReaderDto;
import com.example.storebook.dto.StorageDto;
import com.example.storebook.model.Reader;
import com.example.storebook.utils.mapper.CycleAvoidingMappingContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author pashtet
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReaderMapperTest {
    StorageDto storage = StorageDto
            .builder()
            .name("Библиотека №1")
            .build();

    ReaderDto readerDto = ReaderDto
            .builder()
            .firstName("Иван")
            .lastName("Иванов")
            .build();

    @Autowired
    StorageMapper storageMapper;

    @Autowired
    CycleAvoidingMappingContext context;
    @Autowired
    ReaderMapper mapper;
    Reader reader;

    @BeforeAll
    void init() {
        reader = Reader
                .builder()
                .id(1L)
                .firstName("Иван")
                .lastName("Иванов")
                .storage(storageMapper.toEntity(storage))
                .build();
    }

    @Test
    void readerToReaderDto() {
        assertInstanceOf(ReaderDto.class, mapper.toDto(reader, context));
    }

    @Test
    void readerDtoToReader() {
        assertInstanceOf(Reader.class, mapper.toEntity(readerDto));
    }
}