package edu.danny.agendacontactos.controllers.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class design to handle all user Sign up Request data as a JSON Body.
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-5
 * @see <a href = "http://rdani2005.works" /> About the Creator </a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String identification;
    private String name;
    private String email;
    private String login;
    private String password;
    private long role_id;
}
