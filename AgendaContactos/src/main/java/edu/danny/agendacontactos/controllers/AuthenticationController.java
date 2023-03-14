package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.controllers.security.AuthenticationRequest;
import edu.danny.agendacontactos.controllers.security.AuthenticationResponse;
import edu.danny.agendacontactos.controllers.security.RegisterRequest;
import edu.danny.agendacontactos.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web controller of all User info for Sign in and Sign up
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-5
 * @see <a href = "http://rdani2005.works" /> About the Creator </a>
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    /**
     * Function that creates a new user
     * 
     * @param request New user information.
     * @return JWT from user.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(userService.register(request));
    }

    /**
     * Function that allows user to login into the program.
     * 
     * @param request Used as a JSon Request body.
     * @return JWT Token for the client to be allowed to do a lot of stuff
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
