package ai.ecma.appbranchservice.service;

import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface RegionService {

    ApiResult<List<RegionDTO>> getAll();

    ApiResult<RegionDTO> getById(UUID id);

    ApiResult<RegionDTO> create(RegionDTO regionDTO);

    ApiResult<RegionDTO> edit(UUID id, RegionDTO regionDTO);

    ApiResult<?> delete(UUID id);
}
