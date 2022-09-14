package com.example.storebook.repository;

import com.example.storebook.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pashtet
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Long countBooksByStoragesId(Long id);
}
