package edu.danny.agendacontactos.config;

import edu.danny.agendacontactos.models.User;
import edu.danny.agendacontactos.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Class that handles all the filters for the JWT Token from Client
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-5
 * @see <a href = "http://rdani2005.works" /> About the Creator </a>
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Using method to filter all JWT headers
     * 
     * @param request client request
     * @param response response
     * @param filterChain filter to the user.
     * @throws ServletException Server Exception
     * @throws IOException Internet Exception
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Getting the JWT
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt); // Checking value
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //user exists?
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); // Vallidating token
            if (jwtService.isTokenValid(jwt, (User) userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( 
                          userDetails,
                        null,
                        userDetails.getAuthorities()
                ); 
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // Successfull?
    }
}
