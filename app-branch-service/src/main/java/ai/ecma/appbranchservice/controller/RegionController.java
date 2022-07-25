package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(RegionController.REGION_CONTROLLER)
public interface RegionController {
    String REGION_CONTROLLER = AppConstant.BASE_PATH_V1 + "/region";

    @GetMapping
    ApiResult<List<RegionDTO>> getAll();

    @GetMapping("/{id}")
    ApiResult<RegionDTO> getById(@PathVariable UUID id);

    @PostMapping
    ApiResult<RegionDTO> create(@RequestBody @Valid RegionDTO regionDTO);

    @PutMapping("/{id}")
    ApiResult<RegionDTO> edit(@PathVariable UUID id, @Valid @RequestBody RegionDTO regionDTO);

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);
}
