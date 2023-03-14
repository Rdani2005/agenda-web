package edu.danny.agendacontactos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User information to add to BD.
 */
@Getter
@Setter
public class UserDto {
    private String identification;
    private String name;
    private Date register_day;
    private String email;
    private String login;
    private String password;
    private long role_id;
}
