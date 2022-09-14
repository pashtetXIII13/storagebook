package com.example.storebook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * @author pashtet
 */
@Data
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    Long id;
    @NonNull
    String title;
    @NonNull
    String author;
    GenreDto genre;
    Set<StorageDto> storages;
    Set<ReaderDto> readers;
}
