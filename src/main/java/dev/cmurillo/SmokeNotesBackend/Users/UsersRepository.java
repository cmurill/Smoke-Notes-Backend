package dev.cmurillo.SmokeNotesBackend.Users;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

public interface UsersRepository extends ListCrudRepository<Users, String> {

    Optional<Users> findByUserEmailAndUsername(String userEmail, String username);
    Optional<Users> findByUserEmail(String userEmail);
    Optional<Users> findUserByUsername(String username);
}