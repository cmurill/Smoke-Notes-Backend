package dev.cmurillo.SmokeNotesBackend.Model.Users;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "_user")
public class User {

    @Id
    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected User() {}

    public User(
            String userId,
            String firstName,
            String lastName,
            String username,
            String password,
            Role role) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @PrePersist
    public void handleUserId() {
        if (this.userId == null || this.userId.isBlank()) {
            this.userId = UUID.randomUUID().toString();
        }
    }
}
