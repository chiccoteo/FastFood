package ai.ecma.apporderservice.controller;

import ai.ecma.apporderservice.payload.CardDTO;
import ai.ecma.apporderservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(StripeController.STRIPE_CONTROLLER)
public interface StripeController {
    String STRIPE_CONTROLLER = AppConstant.BASE_PATH_V1 + "/stripe";

    @PostMapping("/token")
    ApiResult<String> createToken(@RequestBody @Valid CardDTO cardDTO);
}
