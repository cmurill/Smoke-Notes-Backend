package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Model.Users.Role;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserRepository;
import dev.cmurillo.SmokeNotesBackend.Auth.AuthenticationRequest;
import dev.cmurillo.SmokeNotesBackend.Auth.AuthenticationResponse;
import dev.cmurillo.SmokeNotesBackend.Auth.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

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
        log.info(getClass() + ": A new user " + registeringUser.getFullName() + " has been registered successfully.");
        var jwt = jwtService.generateToken(registeringUser);
        log.info(getClass() + ": A jwt token has been successfully created for " + registeringUser.getFullName() + ".");
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
