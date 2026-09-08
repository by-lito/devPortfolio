package springboot.ejs.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @Column(name = "country_id", length = 2)
    private String id;
    @Column(name = "country_name", length = 40)
    private String countryName;
    @ManyToOne
    @JoinColumn(name = "region_id")
// BUENA PRÁCTICA: Excluimos las relaciones del toString y equals
// para evitar que Hibernate haga consultas extra o entre en bucle.
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Region region;
}
