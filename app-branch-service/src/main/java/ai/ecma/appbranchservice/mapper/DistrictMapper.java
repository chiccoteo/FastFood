package ai.ecma.appbranchservice.mapper;

import ai.ecma.library.entity.District;
import ai.ecma.library.payload.DistrictDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
    @Mapping(target = "regionId", source = "region.id")
    DistrictDto toDto(District district);

    List<DistrictDto> toDto(List<District> districts);
}
