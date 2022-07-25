package ai.ecma.apporderservice.controller;

import ai.ecma.apporderservice.payload.CardDTO;
import ai.ecma.apporderservice.service.StripeService;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripeControllerImpl implements StripeController {
    private final StripeService stripeService;

    @Override
    public ApiResult<String> createToken(CardDTO cardDTO) {
        return stripeService.createToken(cardDTO);
    }
}
