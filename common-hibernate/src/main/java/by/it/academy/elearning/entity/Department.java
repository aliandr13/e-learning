package by.it.academy.elearning.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DEPARTAMENT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTAMENT_ID", unique = true)
    private Long departamentId;
    @Column(name = "NAME")
    private String departamentName;
    @OneToMany(mappedBy = "departament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
    public Department(String name) {
        this.departamentName = name;
    }
}
