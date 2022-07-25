package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private UUID id;
    @NotBlank
    private String name;

    private double price;

    private String description;

    private boolean active;

    private UUID attachmentId;

    private UUID categoryId;

    private UUID discountId;
}
