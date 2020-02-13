package by.it.academy.elearning.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "group_t")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupStatus status = GroupStatus.PLANED;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group_link",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @BatchSize(size = 30)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime created;
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updated;
}
