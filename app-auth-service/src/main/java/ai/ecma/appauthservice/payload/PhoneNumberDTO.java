package ai.ecma.appauthservice.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneNumberDTO {
    @NotBlank
    private String phoneNumber;
}
