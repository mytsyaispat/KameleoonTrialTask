package KameleoonTrialTask.auth.service;

import KameleoonTrialTask.auth.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    ResponseEntity<String> createUser(User userData);

    Optional<User> getUserByUsername(String username);
}
