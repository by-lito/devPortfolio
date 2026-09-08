package springboot.ejs.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "department_name", nullable = false, length = 30)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "manager_id")
    private Long managerId;

    // 🔥 CORTAMOS AQUÍ
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;
}

