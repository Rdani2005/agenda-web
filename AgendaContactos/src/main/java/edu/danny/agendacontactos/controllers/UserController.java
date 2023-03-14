package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.controllers.security.RegisterRequest;
import edu.danny.agendacontactos.models.User;
import edu.danny.agendacontactos.services.JwtService;
import edu.danny.agendacontactos.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Rest API Controller for all user handlers
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-5
 * @see <a href = "http://rdani2005.works" /> About the Creator </a>
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    /**
     * Function that adds a new user to the program.
     * @param request user information
     * @return new user from DB.
     */
    @PostMapping("/add")
    public User add(@RequestBody RegisterRequest request) {
        return userService.addUser(request);
    }


    /**
     * Function that gets a list of user from DB.
     * @return User list available.
     */
    @GetMapping("/all")
    public ArrayList<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/verify")
    public UserDetails verify(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").replaceAll("^Bearer\\s+", "");
        return userService.loadUserByUsername(
                jwtService.extractUsername(jwt)
        );
    }
}
