package ai.ecma.appauthservice.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OffertaDTO {
    @Column(columnDefinition = "text")
    private String context;
}
