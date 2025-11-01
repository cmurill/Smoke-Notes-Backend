package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Exceptions.UserNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        log.info(getClass() + ": Getting all users in the database");
        List<User> users = userRepository.findAll();
        log.info(getClass() + ": Retrieved {} users from the database",  users.size());
        return users;
    }

    public User getUserById(String id) {
        log.info(getClass() + ": Searching for a user with the id {}", id);
        return checkUserById(id);
    }

    /*
    Register request should replace the create user functionality
    public void createUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            throw new ExistingUserException("Error: A user with this email already exists in the database.");
        }
        userRepository.save(user);
    }
     */

    public void updateUser(String id, User updatedUser) {
        log.info(getClass() + ": Attempting to update a user with id {}", id);
        User existingUser = checkUserById(id);
        User newUser = new User(existingUser.getUserId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getUsername(),
                updatedUser.getPassword(),
                updatedUser.getRole());
        userRepository.save(newUser);
        log.info(getClass() + ": Successfully updated the user to {}", newUser);
    }

    public void deleteUser(String id) {
        log.info(getClass() + ": Attempting to delete a user with id {}", id);
        User userToDelete = checkUserById(id);
        userRepository.delete(userToDelete);
        log.info(getClass() + ": Successfully deleted user {}", userToDelete);
    }

    // Helper method
    private User checkUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.warn(getClass() + ": User not found with id={}", id);
            throw new UserNotFoundException("User with id: " + id + ", could not be found.");
        }
        log.info(getClass() + ": User found with id={}", id);
        return user.get();
    }
}
