package com.example.storebook.services;

import com.example.storebook.dto.ReaderDto;
import com.example.storebook.exception.ReaderNotFoundException;
import com.example.storebook.exception.StorageNotFoundException;
import com.example.storebook.mapper.ReaderMapper;
import com.example.storebook.model.Reader;
import com.example.storebook.repository.ReaderRepository;
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
public class ReaderServiceImpl implements ReaderService {

    private static final String NOT_FOUND_ID = "Reader not found: id = ";
    ReaderRepository repository;
    CycleAvoidingMappingContext context;
    ReaderMapper mapper;

    @Override
    public ReaderDto getReader(Long id) {
        Reader reader = repository
                .findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(NOT_FOUND_ID + id));

        return mapper.toDto(reader, context);
    }

    @Override
    public ReaderDto addReader(ReaderDto reader) {
        return mapper.toDto(repository.save(mapper.toEntity(reader)), context);
    }

    @Override
    public List<ReaderDto> getReaders() {
        Iterable<Reader> readers = repository.findAll();
        List<ReaderDto> readersDto = new ArrayList<>();
        readers.forEach(reader -> readersDto.add(mapper.toDto(reader, context)));
        return readersDto;
    }

    @Override
    public void deleteReader(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn(e.getMessage());
            throw new StorageNotFoundException(NOT_FOUND_ID + id);
        }
    }

    @Override
    public void deleteReaders() {
        repository.deleteAll();
    }
}
