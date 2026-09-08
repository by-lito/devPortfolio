package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ejs.dominio.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
