package dev.cmurillo.SmokeNotesBackend.Auth;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password
) {
}
