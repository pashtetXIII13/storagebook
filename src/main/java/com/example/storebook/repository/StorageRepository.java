package com.example.storebook.repository;

import com.example.storebook.model.Storage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pashtet
 */
@Repository
public interface StorageRepository extends CrudRepository<Storage, Long> {
}
