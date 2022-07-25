package ai.ecma.library.entity.templete;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Created by Kholmakhmatov on 15/06/2022 !
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AbsAuditor {

    @CreatedBy
    private UUID createdBy;

    @LastModifiedBy
    private UUID updatedBy;
}
