package KameleoonTrialTask.logic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vote_sum")
public class VoteSum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private int score = 0;

    public VoteSum(Long id, int score) {
        this.id = id;
        this.score = score;
    }

    public VoteSum() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int result) {
        this.score = score;
    }

    public void fromMinusToPlus() {
        score += 2;
    }

    public void fromPlusToMinus() {
        score -= 2;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        score--;
    }

}
