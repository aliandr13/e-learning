package by.it.academy.elearning.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "el_user")
public class ELUser extends BaseModel {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank
    @Size(min = 5, max = 80)
    @Column(nullable = false)
    private String password;
    @NonNull
    @Column(nullable = false, length = 25)
    private String role;

}
