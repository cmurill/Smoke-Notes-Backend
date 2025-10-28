package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Exceptions.ExistingUserException;
import dev.cmurillo.SmokeNotesBackend.Exceptions.UserNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
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
        User existingUser = checkUserById(id);
        User newUser = new User(existingUser.getUserId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getUsername(),
                updatedUser.getPassword(),
                updatedUser.getRole());
        userRepository.save(newUser);
    }

    public void deleteUser(String id) {
        User userToDelete = checkUserById(id);
        userRepository.delete(userToDelete);
    }

    // Helper method
    private User checkUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + ", could not be found.");
        }
        return user.get();
    }
}
