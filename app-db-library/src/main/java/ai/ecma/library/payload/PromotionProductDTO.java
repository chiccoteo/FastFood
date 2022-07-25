package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionProductDTO {

    private int count;

    private String groupName;

    private ProductDTO product;
}
