package ai.ecma.appbranchservice.service;

import ai.ecma.appbranchservice.common.MessageService;
import ai.ecma.appbranchservice.exception.RestException;
import ai.ecma.appbranchservice.mapper.DistrictMapper;
import ai.ecma.appbranchservice.utils.CommonUtils;
import ai.ecma.library.entity.District;
import ai.ecma.library.entity.Region;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.DistrictDto;
import ai.ecma.library.payload.UserDTO;
import ai.ecma.library.repository.DistrictRepository;
import ai.ecma.library.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;

    private final RegionRepository regionRepository;

    private final DistrictMapper districtMapper;


    @Override
    public ApiResult<List<DistrictDto>> getAll() {
        List<District> all = districtRepository.findAll(Sort.by(District.Fields.name));
        List<DistrictDto> districtDtoList = districtMapper.toDto(all);
        return ApiResult.successResponse(districtDtoList);
    }

    @Override
    public ApiResult<DistrictDto> getById(UUID id) {
        District district = districtRepository.findById(id).orElseThrow(() -> RestException.notFound("DISTRICT_NOT_FOUND"));
        DistrictDto districtDto = districtMapper.toDto(district);
        return ApiResult.successResponse(districtDto);
    }

    @Override
    public ApiResult<DistrictDto> create(DistrictDto districtDto) {

        UserDTO user = CommonUtils.getCurrentUser();
        System.out.println(user.getId());

        District district = new District();
        Region region = regionRepository.findById(districtDto.getRegionId()).orElseThrow(() -> RestException.notFound("REGION_NOT_FOUND"));
        district.setName(districtDto.getName());
        district.setRegion(region);
        District save = districtRepository.save(district);
        return ApiResult.successResponse(districtMapper.toDto(save), MessageService.getMessage("DISTRICT_SAVED"));
    }

    @Override
    public ApiResult<DistrictDto> edit(UUID id, DistrictDto districtDto) {
        District district = districtRepository.findById(id).orElseThrow(() -> RestException.notFound("DISTRICT_NOT_FOUND"));
        Region region = regionRepository.findById(districtDto.getRegionId()).orElseThrow(() -> RestException.notFound("REGION_NOT_FOUND"));
        district.setRegion(region);
        district.setName(districtDto.getName());
        districtRepository.save(district);
        return ApiResult.successResponse(districtMapper.toDto(district));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        District district = districtRepository.findById(id).orElseThrow(() -> RestException.notFound("DISTRICT_NOT_FOUND"));
        districtRepository.delete(district);
        return ApiResult.successResponse(MessageService.getMessage("DISTRICT_DELETED"));
    }
}
