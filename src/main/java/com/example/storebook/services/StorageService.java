package com.example.storebook.services;


import com.example.storebook.dto.StorageDto;

import java.util.List;

/**
 * @author pashtet
 */
public interface StorageService {
    StorageDto getStorage(Long id);

    List<StorageDto> getStorages();

    StorageDto addStorage(StorageDto storageDto);

    void deleteStorage(Long id);

    void deleteStorages();
}
