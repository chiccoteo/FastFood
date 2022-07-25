package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.DistrictDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(DistrictController.REGION_CONTROLLER)
public interface DistrictController {
    String REGION_CONTROLLER = AppConstant.BASE_PATH_V1 + "/district";

    @GetMapping
    ApiResult<List<DistrictDto>> getAll();

    @GetMapping("/{id}")
    ApiResult<DistrictDto> getById(@PathVariable UUID id);

    @PostMapping
    ApiResult<DistrictDto> create(@RequestBody @Valid DistrictDto districtDto);

    @PutMapping("/{id}")
    ApiResult<DistrictDto> edit(@PathVariable UUID id, @RequestBody @Valid DistrictDto districtDto);

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);
}
