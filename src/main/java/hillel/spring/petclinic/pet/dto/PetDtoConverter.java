package hillel.spring.petclinic.pet.dto;

import javax.swing.*;

import java.util.Optional;

import hillel.spring.petclinic.pet.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PetDtoConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Pet toModel(PetInputDto dto);

    @Mapping(target = "version", ignore = true)
    Pet toModel(PetInputDto dto, Integer id);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    void update(@MappingTarget Pet pet, PetInputDto dto);

    default <T> T unpack(Optional<T> maybe){
        return maybe.orElse(null);
    }
}
