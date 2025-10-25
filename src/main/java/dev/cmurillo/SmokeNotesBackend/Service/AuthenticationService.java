package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Model.Users.Role;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserRepository;
import dev.cmurillo.SmokeNotesBackend.auth.AuthenticationRequest;
import dev.cmurillo.SmokeNotesBackend.auth.AuthenticationResponse;
import dev.cmurillo.SmokeNotesBackend.auth.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var registeringUser = new User(
                "",
                request.firstName(),
                request.lastName(),
                request.username(),
                passwordEncoder.encode(request.password()),
                Role.USER
        );
        userRepository.save(registeringUser);
        var jwt = jwtService.generateToken(registeringUser);
        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));
        var user = userRepository.findByUsername(request.username()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
}
