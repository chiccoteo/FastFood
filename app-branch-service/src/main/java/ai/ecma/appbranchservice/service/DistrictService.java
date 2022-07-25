package ai.ecma.appbranchservice.service;

import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.DistrictDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface DistrictService {

    ApiResult<List<DistrictDto>> getAll();

    ApiResult<DistrictDto> getById(@PathVariable UUID id);

    ApiResult<DistrictDto> create(@RequestBody DistrictDto districtDto);

    ApiResult<DistrictDto> edit(@PathVariable UUID id, @RequestBody DistrictDto districtDto);

    ApiResult<?> delete(@PathVariable UUID id);
}
