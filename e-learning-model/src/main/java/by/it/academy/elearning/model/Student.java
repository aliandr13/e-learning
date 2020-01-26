package by.it.academy.elearning.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_t")
public class Student extends Person {

    @Builder
    public Student(String firstName, String middleName, String lastName, String phone, String email, Float grade,
                   User user, Group group) {
        super(firstName, middleName, lastName, phone, email, user);
        this.grade = grade;
        this.group = group;
    }

    private Float grade;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;
}
