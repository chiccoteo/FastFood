package ai.ecma.appbranchservice.service;


import ai.ecma.appbranchservice.common.MessageService;
import ai.ecma.appbranchservice.exception.RestException;
import ai.ecma.appbranchservice.mapper.AddressMapper;
import ai.ecma.library.entity.Address;
import ai.ecma.library.entity.District;
import ai.ecma.library.payload.AddressDTO;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.repository.AddressRepository;
import ai.ecma.library.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepo;
    private final DistrictRepository districtRepo;
    private final AddressMapper addressMapper;

    @Override
    public ApiResult<List<AddressDTO>> getAll() {
        List<Address> addressList = addressRepo.findAll(Sort.by(Address.Fields.name));
        List<AddressDTO> addressDTOList = addressMapper.toDTo(addressList);
        return ApiResult.successResponse(addressDTOList);
    }

    @Override
    public ApiResult<AddressDTO> getById(UUID id) {
        Address address = addressRepo.findById(id).orElseThrow(() -> RestException.notFound("ADDRESS_NOT_FOUND"));
        return ApiResult.successResponse(addressMapper.toDTO(address));
    }

    @Override
    public ApiResult<AddressDTO> create(AddressDTO addressDTO) {
        Address address = new Address();
        address.setName(addressDTO.getName());
        address.setLan(addressDTO.getLan());
        address.setLat(addressDTO.getLat());

        Optional<District> byId = districtRepo.findById(addressDTO.getDistrictId());
        if (byId.isPresent()) {
            District district = byId.get();
            address.setDistrict(district);
        }

        addressRepo.save(address);
        return ApiResult.successResponse(addressMapper.toDTO(address), MessageService.getMessage("ADDRESS_SAVED"));
    }

    @Override
    public ApiResult<AddressDTO> edit(UUID id, AddressDTO addressDTO) {
        Address address = addressRepo.findById(id).orElseThrow(() -> RestException.notFound("ADDRESS_NOT_FOUND"));
        District district = districtRepo.findById(addressDTO.getDistrictId()).orElseThrow(() -> RestException.notFound("DISTRICT_NOT_FOUND"));
        address.setName(addressDTO.getName());
        address.setLan(addressDTO.getLan());
        address.setLat(addressDTO.getLat());
        address.setDistrict(district);
        Address save = addressRepo.save(address);
        return ApiResult.successResponse(addressMapper.toDTO(save), MessageService.getMessage("ADDRESS_EDITED"));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        Address address = addressRepo.findById(id).orElseThrow(() -> RestException.notFound("ADDRESS_NOT_FOUND"));
        addressRepo.delete(address);
        return ApiResult.successResponse(MessageService.getMessage("ADDRESS_DELETED"));
    }
}
