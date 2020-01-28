package by.it.academy.elearning.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "HEROES_POWER")
    private List<Power> power = new ArrayList<>();
}
