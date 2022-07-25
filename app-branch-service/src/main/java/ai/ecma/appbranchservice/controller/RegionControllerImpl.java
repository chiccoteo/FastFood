package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.service.RegionService;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RegionControllerImpl implements RegionController{
    private final RegionService regionService;

    @Override
    public ApiResult<List<RegionDTO>> getAll() {
        return regionService.getAll();
    }

    @Override
    public ApiResult<RegionDTO> getById(UUID id) {
        return regionService.getById(id);
    }

    @Override
    public ApiResult<RegionDTO> create(RegionDTO regionDTO) {
        return regionService.create(regionDTO);
    }

    @Override
    public ApiResult<RegionDTO> edit(UUID id, RegionDTO regionDTO) {
        return regionService.edit(id, regionDTO);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return regionService.delete(id);
    }
}
