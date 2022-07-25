package ai.ecma.appauthservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyPhoneDTO {

    private String verifyCode;

    private String phoneNumber;
}
