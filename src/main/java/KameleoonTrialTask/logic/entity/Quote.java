package KameleoonTrialTask.logic.entity;

import KameleoonTrialTask.auth.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id")
    private List<Vote> voteList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "vote_sum_id", referencedColumnName = "id")
    private VoteSum voteSum;

    public Quote(String content, LocalDateTime date, User user, VoteSum voteSum) {
        this.content = content;
        this.date = date;
        this.user = user;
        this.voteSum = voteSum;
    }

    public Quote() {}

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @JsonIgnore
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    @JsonIgnore
    public void setVoteList(List<Vote> voteList) {
        this.voteList = new ArrayList<>(voteList);
    }

    public void addVote(Vote vote) {
        voteList.add(vote);
    }

    public User getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

    public VoteSum getVoteSum() {
        return voteSum;
    }

    @JsonIgnore
    public void setVoteSum(VoteSum voteSum) {
        this.voteSum = voteSum;
    }
}
