package springboot.ejs.dominio;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_history")
@IdClass(JobHistoryId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobHistory {

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Employee employee;

    @Id
    @Column(name = "start_date")
    @EqualsAndHashCode.Include
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @ToString.Exclude
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @ToString.Exclude
    private Department department;
}
