package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.exception.RestException;
import ai.ecma.appauthservice.mapper.OffertaMapper;
import ai.ecma.appauthservice.payload.OffertaDTO;
import ai.ecma.library.entity.Constants;
import ai.ecma.library.enums.ConstantIdEnum;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.repository.ConstantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OffertaServiceImpl implements OffertaService {
    private final OffertaMapper offertaMapper;
    private final ConstantsRepository constantsRepository;


    @Override
    public ApiResult<OffertaDTO> get(ConstantIdEnum idEnum) {
        Constants offerta = constantsRepository.findById(idEnum).orElseThrow(() -> RestException.notFound("OFFERTA_NOT_FOUND"));
        return ApiResult.successResponse(offertaMapper.toDTO(offerta));
    }

    @Override
    public ApiResult<OffertaDTO> create(OffertaDTO offertaDTO) {
        if (constantsRepository.existsById(ConstantIdEnum.OFERTA))
            return ApiResult.errorResponse("OFFERTA_ALREADY_EXIST");

        Constants offerta = new Constants(ConstantIdEnum.OFERTA,offertaDTO.getContext());
        constantsRepository.save(offerta);
        return ApiResult.successResponse(offertaMapper.toDTO(offerta),"OFFERTA_SAVED");
    }

    @Override
    public ApiResult<OffertaDTO> edit(ConstantIdEnum idEnum, OffertaDTO offertaDTO) {
        Constants offerta = constantsRepository.findById(idEnum).orElseThrow(() -> RestException.notFound("OFFERTA_NOT_FOUND"));
        offerta.setContent(offertaDTO.getContext());
        constantsRepository.save(offerta);
        return ApiResult.successResponse(offertaMapper.toDTO(offerta),"OFFERTA_EDITED");
    }

    @Override
    public ApiResult<OffertaDTO> delete(ConstantIdEnum idEnum) {
        Constants offerta = constantsRepository.findById(idEnum).orElseThrow(() -> RestException.notFound("OFFERTA_NOT_FOUND"));
        constantsRepository.delete(offerta);
        return ApiResult.successResponse(offertaMapper.toDTO(offerta),"OFFERTA_EDITED");
    }
}
