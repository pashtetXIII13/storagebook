package com.example.storebook.mapper;

import com.example.storebook.dto.GenreDto;
import com.example.storebook.model.Genre;
import com.example.storebook.utils.mapper.CycleAvoidingMappingContext;
import com.example.storebook.utils.mapper.MapperUtils;
import org.mapstruct.*;

/**
 * @author pashtet
 */
@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {MapperUtils.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GenreMapper {

    GenreDto toDto(Genre genre, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    Genre toEntity(GenreDto genreDto);
}
