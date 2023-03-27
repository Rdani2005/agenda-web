package edu.danny.agendacontactos.responses;

import lombok.*;

/**
 * BASIC INFO AUTH DATA
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private long id;
    private String identification;
    private String name;
    private String email;
    private String login;
}
