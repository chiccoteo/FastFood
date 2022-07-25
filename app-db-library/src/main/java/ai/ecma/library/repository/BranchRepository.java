package ai.ecma.library.repository;

import ai.ecma.library.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    @Query(value = "with result as (select b.*,\n" +
            "                       (sqrt(pow(69.1 * (cast(:client_lat as float) - cast(a.lat as float)), 2) +\n" +
            "                             pow(69.1 * (cast(:client_lon as float) - cast(a.lan as float)) * cos(cast(:client_lat as float) / 57.3), 2)\n" +
            "                            ) * 1.609344) as distance\n" +
            "                from branch b\n" +
            "                         join address a on b.address_id = a.id\n" +
            "                         join schedule s on b.id = s.branch_id\n" +
            "                where b.active and (:order_time between s.start_time and s.end_time and s.week = to_char(now(), 'DAY')))\n" +
            "select * from result r where distance <= max_radius order by distance limit 1;", nativeQuery = true)
    Optional<Branch> getClosestBranch(@Param("order_time") LocalTime order_time,
                                      @Param("client_lat") double client_lat,
                                      @Param("client_lon") double client_lon);
}
