package KameleoonTrialTask.auth.controller;

import KameleoonTrialTask.auth.entity.User;
import KameleoonTrialTask.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("user")
    public ResponseEntity<String> createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

}
