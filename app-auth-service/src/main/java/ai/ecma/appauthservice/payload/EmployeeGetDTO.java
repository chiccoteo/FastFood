package ai.ecma.appauthservice.payload;

import ai.ecma.library.enums.Language;
import ai.ecma.library.enums.RoleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class EmployeeGetDTO {
    @NotBlank
    @Pattern(regexp = "\\+998[0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    private String phoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;


    @NotBlank
    private RoleType roleType;
}
