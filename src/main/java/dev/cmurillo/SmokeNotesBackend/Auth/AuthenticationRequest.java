package dev.cmurillo.SmokeNotesBackend.Auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
