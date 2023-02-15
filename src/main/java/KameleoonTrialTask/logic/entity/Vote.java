package KameleoonTrialTask.logic.entity;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_positive_vote", nullable = false)
    private boolean isPositiveVote;
}
