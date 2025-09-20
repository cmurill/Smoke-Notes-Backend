package dev.cmurillo.SmokeNotesBackend.Users;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record Users(
    @Id
    String userId,
    String firstName,
    String lastName,
    String userEmail,
    String username,
    String password,
    @Version
    Integer version
) {

    public Users {
        if (userId == null || userId.isBlank()) {
            userId = UUID.randomUUID().toString();
        }
    }
}