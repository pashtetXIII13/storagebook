package com.example.storebook.services;

import com.example.storebook.dto.GenreDto;
import com.example.storebook.exception.GenreNotFoundException;
import com.example.storebook.exception.StorageNotFoundException;
import com.example.storebook.mapper.GenreMapper;
import com.example.storebook.model.Genre;
import com.example.storebook.repository.GenreRepository;
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
public class GenreServiceImpl implements GenreService {

    private static final String NOT_FOUND_ID = "Genre not found: id = ";
    GenreRepository repository;
    GenreMapper mapper;
    CycleAvoidingMappingContext context;

    @Override
    public GenreDto addGenre(GenreDto genreDto) {
        return mapper.toDto(repository.save(mapper.toEntity(genreDto)), context);
    }

    @Override
    public List<GenreDto> getGenres() {
        Iterable<Genre> genres = repository.findAll();
        List<GenreDto> genresDto = new ArrayList<>();
        genres.forEach(genre -> genresDto.add(mapper.toDto(genre, context)));
        return genresDto;
    }

    @Override
    public GenreDto getGenre(Long id) throws GenreNotFoundException {
        Genre genre = repository.findById(id).orElseThrow(
                () -> new GenreNotFoundException(NOT_FOUND_ID + id));
        return mapper.toDto(genre, context);
    }

    @Override
    public void deleteGenre(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn(e.getMessage());
            throw new StorageNotFoundException(NOT_FOUND_ID + id);
        }
    }

    @Override
    public void deleteGenres() {
        repository.deleteAll();
    }
}
