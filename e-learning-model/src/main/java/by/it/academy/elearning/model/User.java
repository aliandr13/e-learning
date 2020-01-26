package by.it.academy.elearning.model;


import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_t")
@Builder
@ToString(callSuper = true)
public class User extends BasicEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    private String salt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
