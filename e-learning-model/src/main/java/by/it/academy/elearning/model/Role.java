package by.it.academy.elearning.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_t")
@ToString(callSuper = true)
public class Role extends BasicEntity {

    @Column(unique = true, length = 50, nullable = false)
    private String roleName;

}
