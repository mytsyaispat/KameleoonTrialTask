package KameleoonTrialTask.logic.entity;

import KameleoonTrialTask.auth.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "quote_id"})})
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_positive_vote", nullable = false)
    private boolean isPositiveVote;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false, referencedColumnName = "id")
    private Quote quote;

    public Vote(boolean isPositiveVote, User user, Quote quote) {
        this.isPositiveVote = isPositiveVote;
        this.user = user;
        this.quote = quote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPositiveVote() {
        return isPositiveVote;
    }

    public void setPositiveVote(boolean positiveVote) {
        isPositiveVote = positiveVote;
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
}
