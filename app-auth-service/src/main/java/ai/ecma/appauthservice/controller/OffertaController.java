package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.OffertaDTO;
import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.enums.ConstantIdEnum;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping(OffertaController.OFFERTA_CONTROLLER)
public interface OffertaController {
    String OFFERTA_CONTROLLER= AppConstant.BASE_PATH_V1+"/offerta";

    @GetMapping("/{id}")
    ApiResult<OffertaDTO> get(@RequestParam ConstantIdEnum idEnum);

    @PostMapping
    ApiResult<OffertaDTO> create(@RequestBody OffertaDTO offertaDTO);

    @PutMapping("/{id}")
    ApiResult<OffertaDTO> edit(@RequestParam ConstantIdEnum idEnum, @RequestBody OffertaDTO offertaDTO);

    @DeleteMapping("/{id}")
    ApiResult<OffertaDTO> delete(@RequestParam ConstantIdEnum idEnum);

}
