package ai.ecma.appauthservice.payload;

import ai.ecma.library.enums.Language;
import ai.ecma.library.payload.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeCreateDTO {

    @NotBlank
    private Language language;

    @NotBlank
    @Pattern(regexp = "\\+998[0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    private String phoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private boolean enabled;

    @NotBlank
    private UUID roleId;

}
