package dev.cmurillo.SmokeNotesBackend.Repository;

import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigar;
import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigarId;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCigarRepository extends JpaRepository<UserCigar, UserCigarId> {
    List<UserCigar> findByUser(User user);
}
