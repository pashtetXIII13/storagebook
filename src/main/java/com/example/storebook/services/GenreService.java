package com.example.storebook.services;

import com.example.storebook.dto.GenreDto;

import java.util.List;

/**
 * @author pashtet
 */

public interface GenreService {
    GenreDto addGenre(GenreDto genreDto);

    List<GenreDto> getGenres();

    GenreDto getGenre(Long id);

    void deleteGenre(Long id);

    void deleteGenres();
}
