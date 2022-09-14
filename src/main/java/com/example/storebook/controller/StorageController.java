package com.example.storebook.controller;

import com.example.storebook.dto.StorageDto;
import com.example.storebook.services.StorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pashtet
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageController {

    StorageService service;

    @GetMapping("/storages/{id}")
    @ResponseStatus(HttpStatus.OK)
    StorageDto getStorage(@PathVariable Long id) {
        return service.getStorage(id);
    }

    @GetMapping("/storages")
    @ResponseStatus(HttpStatus.OK)
    public List<StorageDto> getStorages() {
        return service.getStorages();
    }

    @PostMapping("/storages")
    @ResponseStatus(HttpStatus.CREATED)
    public StorageDto addStorage(@RequestBody StorageDto storageDto) {
        return service.addStorage(storageDto);
    }

    @DeleteMapping("/storages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStorage(@PathVariable Long id) {
        service.deleteStorage(id);
    }

    @DeleteMapping("/storages")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStorages() {
        service.deleteStorages();
    }
}
