package ai.ecma.appbranchservice.service;

import ai.ecma.library.payload.AddressDTO;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    ApiResult<List<AddressDTO>> getAll();

    ApiResult<AddressDTO> getById(UUID id);

    ApiResult<AddressDTO> create(AddressDTO addressDto);

    ApiResult<AddressDTO> edit(UUID id, AddressDTO addressDto);

    ApiResult<?> delete(UUID id);
}
