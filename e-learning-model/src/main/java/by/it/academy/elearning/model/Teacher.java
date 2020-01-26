package by.it.academy.elearning.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher_t")
public class Teacher extends Person {

    @Builder
    public Teacher(String firstName, String middleName, String lastName, String phone, String email, String work, User user) {
        super(firstName, middleName, lastName, phone, email, user);
        this.work = work;
    }

    private String work;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_group", joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups = new HashSet<>();

}
