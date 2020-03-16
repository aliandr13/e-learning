package by.it.academy.elearning.core.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder

@Entity
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
