package com.example.storebook.services;

import com.example.storebook.dto.ReaderDto;

import java.util.List;

/**
 * @author pashtet
 */
public interface ReaderService {
    ReaderDto getReader(Long id);

    ReaderDto addReader(ReaderDto reader);

    List<ReaderDto> getReaders();

    void deleteReader(Long id);

    void deleteReaders();
}
