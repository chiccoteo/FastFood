package ai.ecma.appauthservice.payload;

import ai.ecma.appauthservice.common.MessageService;
import ai.ecma.library.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank
    private String firstname;

    private String lastname;

    @NotBlank(message = "{MOST_NOT_BE_PHONE_NUMBER_EMPTY}")
    @Pattern(regexp = "\\+998[0-9]{9}",message = "{WRONG_PHONE_NUMBER_FORMAT}")
    private String phoneNumber;

    @NotBlank
    private String verifyCode;

//    @NotBlank
    private UUID districtId;

    @NotBlank
    private Language language;
}
