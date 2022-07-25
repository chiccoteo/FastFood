package ai.ecma.appauthservice.mapper;

import ai.ecma.appauthservice.payload.OffertaDTO;
import ai.ecma.library.entity.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OffertaMapper {
    OffertaDTO toDTO(Constants offerta);
}
