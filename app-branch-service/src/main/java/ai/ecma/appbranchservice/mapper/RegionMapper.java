package ai.ecma.appbranchservice.mapper;

import ai.ecma.library.entity.Region;
import ai.ecma.library.payload.RegionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionDTO toDTO(Region region);
    List<RegionDTO> toDTO(List<Region> regions);
}
