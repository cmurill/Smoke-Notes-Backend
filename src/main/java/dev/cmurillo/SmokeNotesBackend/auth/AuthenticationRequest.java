package dev.cmurillo.SmokeNotesBackend.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
