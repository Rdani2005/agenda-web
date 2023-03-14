package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.models.UserRole;
import edu.danny.agendacontactos.services.UserRoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class that handles all user role information.
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-5
 * @see <a href = "http://rdani2005.works" /> About the Creator </a>
 */
@RestController
@RequestMapping("/user-role")
@RequiredArgsConstructor
public class UserRoleController {
    
    private final UserRoleService userRoleService;


    /**
     * Adding a new user role into the DB.
     * 
     * @param userRole new user role to add.
     * @return new user role.
     */
    @PostMapping("/add")
    public UserRole add(@RequestBody UserRole userRole) {
        return userRoleService.add(userRole);
    }
}
