package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.OffertaDTO;
import ai.ecma.appauthservice.service.OffertaService;
import ai.ecma.library.enums.ConstantIdEnum;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OffertaControllerImpl implements OffertaController {
    private final OffertaService offertaService;

    @Override
    public ApiResult<OffertaDTO> get(ConstantIdEnum idEnum) {
        return offertaService.get(idEnum);
    }

    @Override
    public ApiResult<OffertaDTO> create(OffertaDTO offertaDTO) {
        return offertaService.create(offertaDTO);
    }

    @Override
    public ApiResult<OffertaDTO> edit(ConstantIdEnum idEnum, OffertaDTO offertaDTO) {
        return offertaService.edit(idEnum,offertaDTO);
    }

    @Override
    public ApiResult<OffertaDTO> delete(ConstantIdEnum idEnum) {
        return offertaService.delete(idEnum);
    }
}
