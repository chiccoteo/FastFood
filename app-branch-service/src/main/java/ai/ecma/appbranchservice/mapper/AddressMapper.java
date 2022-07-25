package ai.ecma.appbranchservice.mapper;

import ai.ecma.library.entity.Address;
import ai.ecma.library.payload.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "districtId", source = "district.id")
    AddressDTO toDTO(Address address);

    List<AddressDTO> toDTo(List<Address> addresses);


}
