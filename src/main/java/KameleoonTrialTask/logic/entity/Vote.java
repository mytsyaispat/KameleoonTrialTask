package KameleoonTrialTask.logic.entity;

import KameleoonTrialTask.auth.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "quote_id"})})
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vote_number", nullable = false)
    private int voteNumber;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false, referencedColumnName = "id")
    private Quote quote;
    private LocalDateTime date = LocalDateTime.now();


    public Vote(int voteNumber, User user, Quote quote) {
        this.voteNumber = voteNumber;
        this.user = user;
        this.quote = quote;

    }

    public Vote() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
