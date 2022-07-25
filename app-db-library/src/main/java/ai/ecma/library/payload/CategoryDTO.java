package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotBlank
    private String name;

    private UUID attachmentId;

    private UUID parentCategoryId;
}
