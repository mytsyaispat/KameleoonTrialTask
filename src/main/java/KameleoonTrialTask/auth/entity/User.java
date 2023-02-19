package KameleoonTrialTask.auth.entity;

import KameleoonTrialTask.logic.entity.Quote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Size(min = 5, max = 50, message = "Username length should be at least 5 and maximum 50 characters!")
    private String username;
    @Column(unique = true)
    @Email
    @NotBlank(message = "Email must not be empty!")
    private String email;
    @Size(min = 5, message = "Password length should be at least 8 characters!")
    @NotBlank(message = "Password must not be empty!")
    private String password;
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    List<Quote> quoteList = new ArrayList<>();

    public User(User userData) {
        username = userData.getUsername();
        email = userData.getEmail();
        dateOfCreation = now();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {}

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User setPasswordAndReturnUser(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    @JsonIgnore
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    @JsonIgnore
    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
