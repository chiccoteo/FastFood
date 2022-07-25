package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchCreateDTO {
    private String name;

    private String description;

    private boolean active;

    private boolean autoDistributed;

    private double maxRadius;

    private String addressName;

    private double lat;

    private double lan;

    private UUID districtId;
}
