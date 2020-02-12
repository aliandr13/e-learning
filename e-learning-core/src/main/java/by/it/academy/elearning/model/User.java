package by.it.academy.elearning.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "phone", length = 50)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserAuth userAuth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Group> groups = new ArrayList<>(2);

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime created;
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updated;
}
