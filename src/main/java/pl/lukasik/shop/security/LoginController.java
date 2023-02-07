package pl.lukasik.shop.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.shop.security.model.User;
import pl.lukasik.shop.security.model.UserRole;
import pl.lukasik.shop.security.repository.UserRepository;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private long expirationTime;
    private String secret;

    public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository,
                           @Value("${jwt.expirationTime}") long expirationTime,
                           @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.expirationTime = expirationTime;
        this.secret = secret;
    }


    @PostMapping("/login")
    public Token login(@RequestBody LoginCredentials loginCredentials) {
        return auth(loginCredentials.getUsername(), loginCredentials.getPassword());
    }

    @PostMapping("/register")
    public Token login(@RequestBody @Valid RegisterCredentials registerCredentials) {
        if (!registerCredentials.getPassword().equals(registerCredentials.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepository.existsByUsername(registerCredentials.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.save(User.builder()
                .username(registerCredentials.getUsername())
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode(registerCredentials.getPassword()))
                .enabled(true)
                .authorities(List.of(UserRole.ROLE_CUSTOMER))
                .build()
        );

        return auth(registerCredentials.getUsername(), registerCredentials.getPassword());
    }

    private Token auth(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        return new Token(token, principal.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .filter(s -> UserRole.ROLE_ADMIN.name().equals(s))
                .map(s -> true)
                .findFirst()
                .orElse(false)
        );
    }

    @Getter
    private static class LoginCredentials {

        private String username;
        private String password;
    }

    @Getter
    private static class RegisterCredentials {

        @Email
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String repeatPassword;
    }

    @Getter
    @AllArgsConstructor
    private static class Token {

        private String token;
        private boolean adminAccess;
    }
}
