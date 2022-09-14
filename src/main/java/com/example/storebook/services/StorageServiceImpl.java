package com.example.storebook.services;

import com.example.storebook.dto.StorageDto;
import com.example.storebook.exception.StorageNotFoundException;
import com.example.storebook.mapper.StorageMapper;
import com.example.storebook.model.Storage;
import com.example.storebook.repository.StorageRepository;
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
public class StorageServiceImpl implements StorageService {

    private static final String NOT_FOUND_ID = "Books storage not found: id = ";
    StorageRepository repository;
    StorageMapper mapper;
    CycleAvoidingMappingContext context;

    @Override
    public StorageDto getStorage(Long id) {
        return mapper.toDto(repository
                .findById(id)
                .orElseThrow(() -> new StorageNotFoundException(NOT_FOUND_ID + id)), context);
    }

    @Override
    public List<StorageDto> getStorages() {
        Iterable<Storage> storages = repository.findAll();
        List<StorageDto> storagesDto = new ArrayList<>();
        storages.forEach(storage -> storagesDto.add(mapper.toDto(storage, context)));
        return storagesDto;
    }

    @Override
    public StorageDto addStorage(StorageDto storageDto) {
        return mapper.toDto(repository.save(mapper.toEntity(storageDto)), context);
    }

    @Override
    public void deleteStorage(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn(e.getMessage());
            throw new StorageNotFoundException(NOT_FOUND_ID + id);
        }
    }

    @Override
    public void deleteStorages() {
        repository.deleteAll();
    }
}
