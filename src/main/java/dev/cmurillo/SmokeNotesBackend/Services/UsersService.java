package dev.cmurillo.SmokeNotesBackend.Services;


import dev.cmurillo.SmokeNotesBackend.Users.Users;
import dev.cmurillo.SmokeNotesBackend.Users.UsersNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users findUserById(String id) {
        return checkUserById(id);
    }

    public void createUser(Users user) {
        Optional<Users> existingUser = usersRepository.findByUserEmail(user.userEmail());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("A user with this email and already exists in the database.");
        }
        usersRepository.save(user);
    }

    public void updateUser(Users updatedUser, String id) {
        Users existingUser = checkUserById(id);
        Users newUser = new Users(existingUser.userId(), updatedUser.firstName(), updatedUser.lastName(), updatedUser.userEmail(), updatedUser.username(), updatedUser.password(), existingUser.version());
        usersRepository.save(newUser);
    }

    public void deleteUser(String id) {
        Users userToDelete = checkUserById(id);
        usersRepository.deleteById(id);
    }

    public Users findUserByEmail(String email) {
        return checkUserByEmail(email);
    }

    public Users findUserByUsername(String username) {
        return checkUserByUsername(username);
    }


    // helper method
    private Users checkUserById(String id) {
        Optional<Users> user = usersRepository.findById(id);

        if (user.isEmpty()) {
            throw new UsersNotFoundException("User with id " + id + ", could not be found.");
        }
        return user.get();
    }

    private Users checkUserByEmail(String email) {
        Optional<Users> userByEmail = usersRepository.findByUserEmail(email);

        if (userByEmail.isEmpty()) {
            throw new UsersNotFoundException("A user with the email: " + email + ", could not be found.");
        }
        return userByEmail.get();
    }

    private Users checkUserByUsername(String username) {
        Optional<Users> userByUsername = usersRepository.findByUsername(username);

        if (userByUsername.isEmpty()) {
            throw new UsersNotFoundException("A user with the username: " + username + ", could not be found.");
        }
        return userByUsername.get();
    }
}
