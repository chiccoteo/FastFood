package ai.ecma.apporderservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PickUpDTO {
    @NotNull(message = "BRANCH_ID ni bermading")
    private UUID branchId;

    @NotNull(message = "TIME ni bermading")
    private LocalTime time;
}
