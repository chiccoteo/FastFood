package ai.ecma.library.repository;

import ai.ecma.library.entity.Constants;
import ai.ecma.library.enums.ConstantIdEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstantsRepository extends JpaRepository<Constants, ConstantIdEnum> {
}
