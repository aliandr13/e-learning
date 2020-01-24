package by.it.academy.elearning.entity;

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
    public Student(String firstName, String middleName, String lastName, String phone, String email, Float grade, String homeWork) {
        super(firstName, middleName, lastName, phone, email);
        this.grade = grade;
        this.homeWork = homeWork;
    }

    private Float grade;
    private String homeWork;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;
}
