package ai.ecma.apporderservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {
    //AGAR BU ID BOLSA ADDRESDAN KELGAN BOSHQA MALUMOTLAR OLINMAYDI
    private UUID id;

    private String name;

    private double lat;

    private double lan;

    private UUID districtId;
}
