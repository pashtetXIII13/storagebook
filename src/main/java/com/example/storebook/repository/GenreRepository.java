package com.example.storebook.repository;

import com.example.storebook.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * @author pashtet
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
