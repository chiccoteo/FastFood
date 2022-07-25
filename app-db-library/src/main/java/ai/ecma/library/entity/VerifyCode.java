package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by Kholmakhmatov on 15/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class VerifyCode extends AbsEntity {

    @Column(nullable = false)
    private String code;

    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;

    private boolean confirmed;

    private boolean reset;

    public VerifyCode(String code, String phoneNumber) {
        this.code = code;
        this.phoneNumber = phoneNumber;
    }
}
