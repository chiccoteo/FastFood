package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Kholmakhmatov on 15/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "deleted = false")
@SQLDelete(sql = "update notification set deleted = true where id = ?")
public class Notification extends AbsEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment photo;

    @Column(name = "sending_time")
    private Timestamp sendingTime;
}
