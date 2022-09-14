package com.example.storebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author pashtet
 */
@Entity
@Table
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Storage extends BaseEntity {
    @NotNull
    String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    List<Reader> readers;
    @ToString.Exclude
    @ManyToMany(mappedBy = "storages", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    List<Book> books;
}
