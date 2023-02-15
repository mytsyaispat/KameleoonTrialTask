package KameleoonTrialTask.logic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(name = "date_of_creation", nullable = false)
    private LocalDateTime dateOfCreation;
    @Column(name = "date_of_update")
    private LocalDateTime dateOfUpdate;
    @OneToOne(cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST
    })
    @JoinColumn(name = "vote_id", referencedColumnName = "id")
    private Vote vote;



}
