package ai.ecma.library.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;

    private ApiResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private ApiResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> successResponse(T data) {
        return new ApiResult<>(true, data);
    }

    public static <T> ApiResult<T> successResponse(T data, String message) {
        return new ApiResult<>(true, message, data);
    }

    public static <T> ApiResult<T> successResponse(String message) {
        return new ApiResult<>(true, message);
    }

    public static <T> ApiResult<T> errorResponse(String message) {
        return new ApiResult<>(false, message);
    }

}
