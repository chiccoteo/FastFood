package ai.ecma.library.entity;

import ai.ecma.library.enums.ConstantIdEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 15/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Constants {
    @Id
    @Enumerated(EnumType.STRING)
    private ConstantIdEnum id;

    @Column(columnDefinition = "text")
    private String content;

}
