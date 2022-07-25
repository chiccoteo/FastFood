package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionDTO {
    private UUID id;

    @NotBlank
    private String name;

    public RegionDTO(String name) {
        this.name = name;
    }
}
