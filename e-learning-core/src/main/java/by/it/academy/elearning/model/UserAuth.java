package by.it.academy.elearning.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "salt", nullable = false)
    private String salt;

    @OneToOne(mappedBy = "userAuth", fetch = FetchType.LAZY)
    private User userInfo;

}
