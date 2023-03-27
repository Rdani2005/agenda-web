package edu.danny.agendacontactos.responses;

import edu.danny.agendacontactos.models.UserRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * REPRESENTS A USER INFO Object
 * 
 * 
 * because of Spring Security and auth, needed to return info data
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalUserResponse {

    private long id;
    private String identification;
    private String name;
    private String email;
    private String login;
    private Set<UserRole> roles = new HashSet<>();
}
