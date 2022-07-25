package ai.ecma.appbranchservice.feign;

import ai.ecma.appbranchservice.exception.RestException;
import ai.ecma.library.payload.ApiResult;
import com.google.gson.Gson;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {
    private final Gson gson;

    @Override
    public Exception decode(String s, Response response) {
        try {
            Response.Body responseBody = response.body();
            byte[] bytes = StreamUtils.copyToByteArray(responseBody.asInputStream());
            ApiResult<?> apiResult = gson.fromJson(new String(bytes), ApiResult.class);
            return RestException.restThrow(apiResult.getMessage(), HttpStatus.valueOf(response.status()));
        } catch (Exception e) {
            throw RestException.conflict("ERROR");
        }

    }
}
