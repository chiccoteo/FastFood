package ai.ecma.apporderservice.service;

import ai.ecma.apporderservice.exception.RestException;
import ai.ecma.apporderservice.payload.CardDTO;
import ai.ecma.library.payload.ApiResult;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${app.stripe.secret-key}")
    private String secretKey;

    public Charge createCharge(String token, Double amount) {
        try {
            Stripe.apiKey = secretKey;

            ChargeCreateParams chargeCreateParams = ChargeCreateParams.builder().setAmount(amount.longValue() * 100).setCurrency("uzs").setSource(token).setCapture(false).setDescription("Bu Fast-Food uchun description").build();
            return Charge.create(chargeCreateParams);
        } catch (StripeException e) {
            e.printStackTrace();
            throw RestException.conflict("UNABLE_CREATE_CHARGE");
        }
    }

    public ApiResult<String> createToken(CardDTO cardDTO) {
        try {
            Stripe.apiKey = secretKey;

            Map<String, Object> card = new HashMap<>();
            card.put("number", cardDTO.getNumber());
            card.put("exp_month", cardDTO.getExpireMonth());
            card.put("exp_year", cardDTO.getExpireYear());
            card.put("cvc", cardDTO.getCvc());

            Map<String, Object> params = new HashMap<>();
            params.put("card", card);

            Token token = Token.create(params);
            return ApiResult.successResponse(token.getId(), "");
        } catch (StripeException e) {
            e.printStackTrace();
            throw RestException.conflict("UNABLE_CREATE_TOKEN");
        }
    }

    public void captureCharge(String chargeId) {
        try {

            Stripe.apiKey = secretKey;

            Charge charge = Charge.retrieve(chargeId);

            Charge updatedCharge = charge.capture();
            System.out.println(updatedCharge);
        } catch (StripeException e) {
            e.printStackTrace();
            throw RestException.conflict("CHARGE QILOLMADIM");
        }
    }
}
