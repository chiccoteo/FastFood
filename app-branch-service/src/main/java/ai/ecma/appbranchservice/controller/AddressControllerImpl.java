package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.service.AddressService;
import ai.ecma.library.payload.AddressDTO;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    @Override
    public ApiResult<List<AddressDTO>> getAll() {
        return addressService.getAll();
    }

    @Override
    public ApiResult<AddressDTO> getById(UUID id) {
        return addressService.getById(id);
    }

    @Override
    public ApiResult<AddressDTO> create(AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @Override
    public ApiResult<AddressDTO> edit(UUID id, AddressDTO addressDTO) {
        return addressService.edit(id, addressDTO);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return addressService.delete(id);
    }
}
