package edu.danny.agendacontactos.services;

import edu.danny.agendacontactos.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for managing JWT objects.
 *
 * @author Danny Sequeira
 * @since 2023-03-06
 */
@Service
public class JwtService {

    //TODO: Change this into a .env file
    private static final String SECRET_KEY = "73367639792F423F4528482B4D6251655468576D5A7134743777217A25432646";

    /**
     * Gets the username by its JWT.
     * @param jwt used for authentication, returning the username.
     * @return Username
     */
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    /**
     * Check if the token has expired or not.
     * @param token token to check
     * @param user User that is using the token
     * @return True or false
     */
    public boolean isTokenValid(String token, User user) {
        final String login = extractUsername(token);
        return (login.equals(user.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Check if user token is used or not.
     * 
     * @param token used for check the token
     * @return True in case user token has expired
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }

    /**
     * Check if user Token is valid or not!
     * 
     * @param token Token received from DB
     * @return User Token expiration date.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Method that is used to extract the claims of the user.
     * 
     * @param jwt            JWT from user
     * @param claimsResolver Claim that we need.
     * @return The required claim
     * @param <T> Anything
     */
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Method that generates a new User Token.
     * 
     * @param extraClaims claims that we want to add to the JWT
     * @param user        user that is generating the JWT
     * @return a JWT with all user data.
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            User user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Returning all claims from a user JWT sent from Frontend.
     * 
     * @param jwt jwt to get claims.
     * @return All Claims available from user.
     */
    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt).getBody();
    }

    /**
     * Method to get the sign in key.
     * 
     * @return Sign In Key
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
