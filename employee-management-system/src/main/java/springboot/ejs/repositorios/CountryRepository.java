package springboot.ejs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ejs.dominio.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
}


