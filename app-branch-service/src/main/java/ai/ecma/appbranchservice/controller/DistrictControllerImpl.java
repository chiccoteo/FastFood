package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.aop.Authorize;
import ai.ecma.appbranchservice.service.DistrictService;
import ai.ecma.library.enums.Permission;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.DistrictDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DistrictControllerImpl implements DistrictController {

    private final DistrictService districtService;

    @Override
    public ApiResult<List<DistrictDto>> getAll() {
        return districtService.getAll();
    }

    @Override
    public ApiResult<DistrictDto> getById(UUID id) {
        return districtService.getById(id);
    }

    @Override
    @Authorize(permissions = {Permission.CREATE_DISTRICT})
    public ApiResult<DistrictDto> create(DistrictDto districtDto) {
        return districtService.create(districtDto);
    }

    @Override
    public ApiResult<DistrictDto> edit(UUID id, DistrictDto districtDto) {
        return districtService.edit(id, districtDto);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return districtService.delete(id);
    }
}
