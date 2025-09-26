package dev.cmurillo.SmokeNotesBackend.Users;

import java.util.List;

import dev.cmurillo.SmokeNotesBackend.Services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // GET
    @GetMapping("")
    List<Users> findAll() {
        return usersService.findAllUsers();
    }

    @GetMapping("/{id}")
    Users findById(@PathVariable String id) {
        return usersService.findUserById(id);
    }

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Users users) {
        usersService.createUser(users);
    }

    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Users newUser, @PathVariable String id) {
        usersService.updateUser(newUser, id);
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        usersService.deleteUser(id);
    }

    @GetMapping("/email/{email}")
    Users findByEmail(@PathVariable String email) {
        return usersService.findUserByEmail(email);
    }

    @GetMapping("/username/{username}")
    Users findByUsername(@PathVariable String username) {
        return usersService.findUserByUsername(username);
    }
}