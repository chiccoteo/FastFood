package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.utils.AppConstant;
import ai.ecma.library.payload.AddressDTO;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@RequestMapping(AddressController.ADDRESS_CONTROLLER)
public interface AddressController {
    String ADDRESS_CONTROLLER = AppConstant.BASE_PATH_V1 + "/address";

    @GetMapping
    ApiResult<List<AddressDTO>> getAll();

    @GetMapping("/{id}")
    ApiResult<AddressDTO> getById(@PathVariable UUID id);

    @PostMapping
    ApiResult<AddressDTO> create(@RequestBody @Valid AddressDTO addressDTO);

    @PutMapping("{id}")
    ApiResult<AddressDTO> edit(@PathVariable UUID id, @RequestBody @Valid AddressDTO addressDTO);

    @DeleteMapping("{id}")
    ApiResult<?> delete(@PathVariable UUID id);
}
