package com.example.storebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author pashtet
 */
@Entity
@Table
@Data
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @NotNull
    String title;
    @NotNull
    String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    @EqualsAndHashCode.Exclude
    Genre genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_storage_id")
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    Set<Storage> storages;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    Set<Reader> readers;
}
