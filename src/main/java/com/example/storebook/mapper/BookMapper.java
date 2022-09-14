package com.example.storebook.mapper;


import com.example.storebook.dto.BookDto;
import com.example.storebook.model.Book;
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
public interface BookMapper {

    BookDto toDto(Book book, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    Book toEntity(BookDto bookDto);

}
