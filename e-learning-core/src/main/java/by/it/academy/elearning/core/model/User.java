package by.it.academy.elearning.core.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = "password")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Entity
@Table(name = "user")
public class User extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 25)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;


}
