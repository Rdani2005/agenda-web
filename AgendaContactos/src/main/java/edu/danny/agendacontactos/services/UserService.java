package edu.danny.agendacontactos.services;

import edu.danny.agendacontactos.controllers.security.AuthenticationRequest;
import edu.danny.agendacontactos.responses.AuthenticationResponse;
import edu.danny.agendacontactos.controllers.security.RegisterRequest;
import edu.danny.agendacontactos.models.Token;
import edu.danny.agendacontactos.models.TokenType;
import edu.danny.agendacontactos.models.User;
import edu.danny.agendacontactos.models.UserRole;
import edu.danny.agendacontactos.repositories.TokenRepository;
import edu.danny.agendacontactos.repositories.UserRepository;
import edu.danny.agendacontactos.responses.GlobalUserResponse;
import edu.danny.agendacontactos.responses.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for managing User objects.
 *
 * @author Danny Sequeira
 * @since 2023-03-06
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    /**
     * Method that get a list of user on BD.
     *
     * @return List of all users on bd.
     */
    public ArrayList<GlobalUserResponse> getAll() {
        List<User> usersBD = (ArrayList<User>) userRepository.findAll();
        ArrayList<GlobalUserResponse> response = new ArrayList<GlobalUserResponse>();

        for(User user : usersBD) {
            response.add(
                    GlobalUserResponse
                            .builder()
                            .id(user.getId())
                            .login(user.getLogin())
                            .identification(user.getIdentification())
                            .name(user.getName())
                            .email(user.getEmail())
                            .roles(user.getRoles())
                            .build()
            );
        }


        return response;
    }

    /**
     * Filter a user by its ID.
     *
     * @param id used as a filter
     * @return Single user object
     */
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    /**
     * Adds a new user into the db.
     *
     * @param request User info to be added.
     * @return The new user
     */
    public User addUser(RegisterRequest request) {

        Set<UserRole> roles = new HashSet<>();
        for (Long roleId : request.getRoleIds()) {
            UserRole role = userRoleService.getById(roleId);
            roles.add(role);
        }

        var user = User.builder()
                .identification(request.getIdentification())
                .name(request.getName())
                .login(request.getLogin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles((Set<UserRole>) roles)
                .register_day(new Date())

                .build();
        return userRepository.save(user);
    }

    /**
     * Deleting user from DB.
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * Checking if a user is available by its password and username.
     *
     * @param request user info.
     * @return Authentication response JWT
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByLogin(request.getLogin()).orElseThrow();
        var JwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, JwtToken);

        return AuthenticationResponse
                .builder()
                .token(JwtToken)
                .user(
                        UserInfoResponse.builder()
                                .id(user.getId())
                                .identification(user.getIdentification())
                                .name(user.getName())
                                .email(user.getEmail())
                                .login(user.getLogin())
                                .build()
                )
                .build();
    }

    /**
     * Register a new user into the DB, without admin permissions.
     *
     * @param request used to get user information.
     * @return Authentication response JWT
     */
    public AuthenticationResponse register(RegisterRequest request) {
        // Returning a new user into the BD.
        User savedUser = addUser(request);
        var jwtToken = jwtService.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Returning a user by its username
     *
     * @param username username to be filtered
     * @return user in case of exists
     */
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByLogin(username);
        return user.orElse(null);
    }

    /**
     * Used to save a new JWT
     *
     * @param user     user that request the new JWT
     * @param jwtToken token to be saved
     */
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * Deleting a user token from DB
     *
     * @param user user that is asking to delete its token
     */
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username).orElseThrow();
        return user;
    }
}
