package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ejs.dominio.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
