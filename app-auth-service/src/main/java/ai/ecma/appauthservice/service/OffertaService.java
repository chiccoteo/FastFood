package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.payload.OffertaDTO;
import ai.ecma.library.enums.ConstantIdEnum;
import ai.ecma.library.payload.ApiResult;
import org.springframework.stereotype.Service;

public interface OffertaService {
    ApiResult<OffertaDTO> get(ConstantIdEnum idEnum);

    ApiResult<OffertaDTO> create(OffertaDTO offertaDTO);

    ApiResult<OffertaDTO> edit( ConstantIdEnum idEnum,  OffertaDTO offertaDTO);

    ApiResult<OffertaDTO> delete( ConstantIdEnum idEnum);
}
