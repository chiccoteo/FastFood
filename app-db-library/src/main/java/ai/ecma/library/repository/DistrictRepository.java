package ai.ecma.library.repository;

import ai.ecma.library.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
}
