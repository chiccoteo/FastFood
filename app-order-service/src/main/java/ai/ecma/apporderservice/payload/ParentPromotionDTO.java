package ai.ecma.apporderservice.payload;

import ai.ecma.library.enums.PromotionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParentPromotionDTO {
    private String name;
    private PromotionTypeEnum type;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
