package com.example.storebook.controller;

import com.example.storebook.dto.ReaderDto;
import com.example.storebook.services.ReaderService;
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
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReaderController {

    ReaderService service;

    @GetMapping("/readers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDto getReader(@PathVariable Long id) {
        return service.getReader(id);
    }

    @GetMapping("/readers")
    @ResponseStatus(HttpStatus.OK)
    public List<ReaderDto> getReaders() {
        return service.getReaders();
    }

    @PostMapping("/readers")
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDto addReader(@RequestBody ReaderDto readerDto) {
        return service.addReader(readerDto);
    }

    @DeleteMapping("/readers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReader(@PathVariable Long id) {
        service.deleteReader(id);
    }

    @DeleteMapping("/readers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReaders() {
        service.deleteReaders();
    }
}
