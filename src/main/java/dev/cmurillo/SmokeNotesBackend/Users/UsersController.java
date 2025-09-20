package dev.cmurillo.SmokeNotesBackend.Users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // GET
    @GetMapping("")
    List<Users> findAll() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    Users findById(@PathVariable String id) {
        Optional<Users> users = usersRepository.findById(id);
        if (users.isEmpty()) {
            throw new UsersNotFoundException("A user with the id: " + id + ", could not be found");
        }
        return users.get();
    }

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Users users) {
        Optional<Users> existingUsers = usersRepository.findByUserEmailAndUsername(
            users.userEmail(),
            users.username());

        if (existingUsers.isPresent()) {
            throw new IllegalArgumentException("This user already exists in the database");
        }
        usersRepository.save(users);
    }

    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Users newUsers, @PathVariable String id) {
        Users existingUsers = findById(id);

        Users updatedUsers = new Users(existingUsers.userId(), newUsers.firstName(), newUsers.lastName(), newUsers.userEmail(), newUsers.username(), newUsers.password(), existingUsers.version());
        usersRepository.save(updatedUsers);
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        Optional<Users> users = usersRepository.findById(id);
        if (users.isEmpty()) {
            throw new UsersNotFoundException("A user with the id: " + id + ", does not exist");
        }
        usersRepository.deleteById(id);
    }

    @GetMapping("/email/{email}")
    Users findByEmail(@PathVariable String email) {
        Optional<Users> usersByEmail = usersRepository.findByUserEmail(email);

        if(usersByEmail.isEmpty()) {
            throw new UsersNotFoundException("A user with the email: " + email + " could not be found.");
        }
        return usersByEmail.get();
    }

    @GetMapping("/username/{username}")
    Users findByUsername(@PathVariable String username) {
        Optional<Users> usersByUsername = usersRepository.findUserByUsername(username);

        if (usersByUsername.isEmpty()) {
            throw new UsersNotFoundException("A user with the username: " + username + " could not be found.");
        }
        return usersByUsername.get();
    }
}