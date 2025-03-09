package clubpedia.in.net.clubpedia.region.repository;

import clubpedia.in.net.clubpedia.region.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> { }
