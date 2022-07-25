package ai.ecma.library.repository;

import ai.ecma.library.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {


}
