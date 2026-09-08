package springboot.ejs.dominio;

import jakarta.persistence.*;
import lombok.*; // Importamos todas las anotaciones de Lombok
@Entity
@Table(name = "regions")
// --- BLOQUE LOMBOK ---
@Data // Genera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacío (Requerido por Hibernate)
@AllArgsConstructor // Constructor con todos los argumentos (Para nosotros)
// ---------------------
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;
    @Column(name = "region_name", length = 25)
    private String regionName;
// ¡Observad qué limpia queda la clase! No hay ruido visual.
}

