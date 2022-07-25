package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    @NotBlank
    private String name;

    private String description;

    private boolean active;

    private boolean autoDistributed;

    private double maxRadius;

    private UUID addressId;
}
