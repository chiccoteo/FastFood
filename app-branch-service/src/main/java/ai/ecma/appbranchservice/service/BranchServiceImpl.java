package ai.ecma.appbranchservice.service;

import ai.ecma.appbranchservice.common.MessageService;
import ai.ecma.appbranchservice.exception.RestException;
import ai.ecma.appbranchservice.mapper.BranchMapper;
import ai.ecma.library.entity.Address;
import ai.ecma.library.entity.Branch;
import ai.ecma.library.entity.District;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.BranchCreateDTO;
import ai.ecma.library.payload.BranchDTO;
import ai.ecma.library.repository.AddressRepository;
import ai.ecma.library.repository.BranchRepository;
import ai.ecma.library.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    private final AddressRepository addressRepository;

    private final DistrictRepository districtRepository;

    private final BranchMapper mapper;

    @Override
    public ApiResult<List<BranchDTO>> getAll() {
        List<Branch> branches = branchRepository.findAll(Sort.by(Branch.Fields.name));
        return ApiResult.successResponse(mapper.toDTO(branches));
    }

    @Override
    public ApiResult<BranchDTO> getById(UUID id) {
        Branch branch = branchRepository.findById(id).
                orElseThrow(() -> RestException.notFound("BRANCH_NOT_FOUND"));
        return ApiResult.successResponse(mapper.toDTO(branch));
    }

    @Override
    public ApiResult<BranchDTO> create(BranchCreateDTO branchCreateDTO) {
        Address address = new Address();
        address.setName(branchCreateDTO.getAddressName());
        address.setLan(branchCreateDTO.getLan());
        address.setLat(branchCreateDTO.getLat());

        if (branchCreateDTO.getDistrictId()!=null) {
            Optional<District> optionalDistrict = districtRepository.findById(branchCreateDTO.getDistrictId());
            if (optionalDistrict.isPresent()) {
                District district = optionalDistrict.get();
                address.setDistrict(district);
            }
        }
        Address saveAddress = addressRepository.save(address);

        Branch branch = new Branch();
        branch.setName(branchCreateDTO.getName());
        branch.setDescription(branchCreateDTO.getDescription());
        branch.setActive(branchCreateDTO.isActive());
        branch.setMaxRadius(branchCreateDTO.getMaxRadius());
        branch.setAutoDistributed(branchCreateDTO.isAutoDistributed());
        branch.setAddress(saveAddress);

        branchRepository.save(branch);

        return ApiResult.successResponse(mapper.toDTO(branch), MessageService.getMessage("BRANCH_SAVED"));
    }

    @Override
    public ApiResult<BranchDTO> edit(UUID id, BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(id).
                orElseThrow(() -> RestException.notFound("BRANCH_NOT_FOUND"));
        Address address = addressRepository.findById(branchDTO.getAddressId()).
                orElseThrow(() -> RestException.notFound("ADDRESS_NOT_FOUND"));

        branch.setName(branchDTO.getName());
        branch.setDescription(branchDTO.getDescription());
        branch.setActive(branchDTO.isActive());
        branch.setAutoDistributed(branchDTO.isAutoDistributed());
        branch.setMaxRadius(branchDTO.getMaxRadius());
        branch.setAddress(address);

        return ApiResult.successResponse(mapper.toDTO(branch), MessageService.getMessage("BRANCH_SAVED"));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        Branch branch = branchRepository.findById(id).
                orElseThrow(() -> RestException.notFound("BRANCH_NOT_FOUND"));
        branchRepository.delete(branch);

        return ApiResult.successResponse("BRANCH_DELETED");
    }
}
