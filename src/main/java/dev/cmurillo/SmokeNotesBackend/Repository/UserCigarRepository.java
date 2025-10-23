package dev.cmurillo.SmokeNotesBackend.Repository;

import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigar;
import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCigarRepository extends JpaRepository<UserCigar, UserCigarId> {
}
