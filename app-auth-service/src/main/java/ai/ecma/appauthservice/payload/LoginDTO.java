package ai.ecma.appauthservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank
    @Pattern(regexp = "\\+998[0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    private String phoneNumber;
    @NotBlank()
    private String password;
}
