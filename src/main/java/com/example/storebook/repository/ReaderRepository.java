package com.example.storebook.repository;

import com.example.storebook.model.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pashtet
 */
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    Long countReadersByStorageId(Long id);
}
