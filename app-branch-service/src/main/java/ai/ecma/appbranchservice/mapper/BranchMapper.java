package ai.ecma.appbranchservice.mapper;


import ai.ecma.library.entity.Branch;
import ai.ecma.library.payload.BranchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    @Mapping(target = "addressId", source = "address.id")
    BranchDTO toDTO(Branch branch);

    List<BranchDTO> toDTO(List<Branch> branches);

    @Mapping(target = "address.id", ignore = true)
    Branch toEntity(BranchDTO branchDTO);

//    @Mapping(target = "address.id", source = "addressId")
//    void update(@MappingTarget Branch branch, BranchDTO branchDTO);
}
