package com.example.storebook.controller;

import com.example.storebook.dto.GenreDto;
import com.example.storebook.services.GenreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pashtet
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class GenreController {

    GenreService service;

    @GetMapping("/genres/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDto getGenre(@PathVariable Long id) {
        return service.getGenre(id);
    }

    @GetMapping("/genres")
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDto> getGenres() {
        return service.getGenres();
    }

    @PostMapping("/genres")
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDto addReader(@RequestBody GenreDto genreDto) {
        return service.addGenre(genreDto);
    }

    @DeleteMapping("/genres/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        service.deleteGenre(id);
    }

    @DeleteMapping("/genres")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReaders() {
        service.deleteGenres();
    }
}
