package ai.ecma.apporderservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDTO {
    @NotBlank
//    @Pattern(regexp = "[1-9]{1}[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}")
    @Pattern(regexp = "[1-9]{1}[0-9]{15}")
    private String number;

    @NotBlank
    @Pattern(regexp = "^([0-1]{1}[0-2]{1}|[1-9]{1})|[0]{1}[1-9]{1}")
    private String expireMonth;

    @NotBlank
    @Pattern(regexp = "[0-9]{2}")
    private String expireYear;

    @NotBlank
    @Pattern(regexp = "[0-9]{3}")
    private String cvc;
}
