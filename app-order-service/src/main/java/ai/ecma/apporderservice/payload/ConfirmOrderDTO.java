package ai.ecma.apporderservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfirmOrderDTO {
    @NotNull(message = "ID ni bermading")
    private UUID id;

    private UUID payTypeId;

    private String description;

    //Promotion Product aksiyadagi product id lari
    private List<UUID> promotionProductIdList;

    // TRUE BOLSA PickUpDTO NI OLISHIMIZ KERAK, AKS HOLDA ADDRES DTO NI OLISHIMIZ KERAK
    private boolean delivery;

    //BU VALIDLARNI TEKSHIRISH KERAK
    @Valid
    private PickUpDTO pickUpDTO;

    @Valid
    private AddressDTO addressDTO;


}
