package com.example.storebook.mapper;


import com.example.storebook.dto.StorageDto;
import com.example.storebook.model.Storage;
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
public interface StorageMapper {

    StorageDto toDto(Storage storage, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    Storage toEntity(StorageDto storageDto);

}
