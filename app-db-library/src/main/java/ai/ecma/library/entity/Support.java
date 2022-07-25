package ai.ecma.library.entity;

import ai.ecma.library.enums.SupportType;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 15/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "update support set deleted = true where id = ?")
public class Support extends AbsEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private SupportType type;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment photo;

}
