package com.example.storebook.services;

import com.example.storebook.repository.BookRepository;
import com.example.storebook.repository.ReaderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * @author pashtet
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Counter {

    ReaderRepository readerRepository;
    BookRepository bookRepository;

    public Long getCountReaders(Long booksStorageId) {
        return readerRepository.countReadersByStorageId(booksStorageId);
    }

    public Long getCountBooks(Long booksStorageId) {
        return bookRepository.countBooksByStoragesId(booksStorageId);
    }
}
