package com.example.storebook.mapper;

import com.example.storebook.dto.ReaderDto;
import com.example.storebook.model.Reader;
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
public interface ReaderMapper {

    ReaderDto toDto(Reader reader, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    Reader toEntity(ReaderDto readerDto);
}
