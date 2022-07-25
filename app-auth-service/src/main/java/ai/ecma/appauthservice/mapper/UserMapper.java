package ai.ecma.appauthservice.mapper;

import ai.ecma.library.entity.User;
import ai.ecma.library.payload.UserDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "districtId", source = "district.id"),
            @Mapping(target = "photoId", source = "photo.id")
    })
    UserDTO toDTO(User user);

    List<UserDTO> toDTO(List<User> user);
}
