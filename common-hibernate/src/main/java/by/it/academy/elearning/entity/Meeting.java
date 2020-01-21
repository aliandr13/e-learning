package by.it.academy.elearning.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MEETING")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private Long meetingId;
    private String subject;
    private LocalDateTime startDate;
    @ManyToMany(mappedBy = "meetings")
    private List<Employee> employees = new ArrayList<>();

    public Meeting(String subject) {
        this.subject = subject;
        this.startDate = LocalDateTime.now();
    }
}
