package ai.ecma.library.repository;

import ai.ecma.library.entity.Role;
import ai.ecma.library.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
public interface RoleRepository extends JpaRepository<Role, UUID> {
     boolean existsByName(String name);
     Optional<Role> findByType(RoleType type);

     boolean existsByType(RoleType type);
}
