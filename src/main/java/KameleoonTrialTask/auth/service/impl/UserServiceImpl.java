package KameleoonTrialTask.auth.service.impl;

import KameleoonTrialTask.auth.entity.User;
import KameleoonTrialTask.auth.repository.UserRepository;
import KameleoonTrialTask.auth.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<String> createUser(User userData) {
        Optional<User> optionalUser = userRepository.findByUsername(userData.getUsername());
        if (optionalUser.isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "This nickname is already taken");
        optionalUser = userRepository.findByEmail(userData.getEmail());
        if (optionalUser.isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already taken");
        User user = new User(userData).setPasswordAndReturnUser(passwordEncoder.encode(userData.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User successfully created!");
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found!");
        return optionalUser.get();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
