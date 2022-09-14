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
public class Reader extends BaseEntity {
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_storage_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Storage storage;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "readers", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Book> books;

}
