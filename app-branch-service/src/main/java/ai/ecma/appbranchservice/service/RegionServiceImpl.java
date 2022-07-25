package ai.ecma.appbranchservice.service;

import ai.ecma.appbranchservice.common.MessageService;
import ai.ecma.appbranchservice.exception.RestException;
import ai.ecma.appbranchservice.mapper.RegionMapper;
import ai.ecma.library.entity.Region;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;
import ai.ecma.library.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public ApiResult<List<RegionDTO>> getAll() {
        List<Region> regionList = regionRepository.findAll(Sort.by(Region.Fields.name));
        List<RegionDTO> regionDTOList = regionMapper.toDTO(regionList);
        return ApiResult.successResponse(regionDTOList);
    }

    @Override
    public ApiResult<RegionDTO> getById(UUID id) {
        Region region = regionRepository.findById(id).orElseThrow(() -> RestException.notFound("REGION_NOT_FOUND"));
        return ApiResult.successResponse(regionMapper.toDTO(region));
    }

    @Override
    public ApiResult<RegionDTO> create(RegionDTO regionDTO) {
        Region region = new Region(regionDTO.getName());
        regionRepository.save(region);
        return ApiResult.successResponse(regionMapper.toDTO(region), MessageService.getMessage("REGION_SAVED"));
    }

    @Override
    public ApiResult<RegionDTO> edit(UUID id, RegionDTO regionDTO) {
        Region region = regionRepository.findById(id).orElseThrow(() -> RestException.notFound("REGION_NOT_FOUND"));
        region.setName(regionDTO.getName());
        regionRepository.save(region);
        return ApiResult.successResponse(regionMapper.toDTO(region));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        Region region = regionRepository.findById(id).orElseThrow(() -> RestException.notFound("REGION_NOT_FOUND"));
        regionRepository.delete(region);
        return ApiResult.successResponse(MessageService.getMessage("REGION_DELETED"));
    }
}
