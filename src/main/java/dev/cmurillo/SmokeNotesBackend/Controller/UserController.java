package dev.cmurillo.SmokeNotesBackend.Controller;

import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    List<User> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User findById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    /*
    Register request should replace the create user functionality
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody User user) {
        userService.createUser(user);
    }
     */

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@PathVariable String id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
