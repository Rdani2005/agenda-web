package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.models.UserRole;
import edu.danny.agendacontactos.services.UserRoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Class that handles all user role information.
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-5
 * @see <a href = "http://rdani2005.works" /> About the Creator </a>
 */
@RestController
@RequestMapping("/api/v1/user-role")
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

    /**
     * Get all user roles
     * @return user role
     */
    @GetMapping("/all")
    public ArrayList<UserRole> all() {return userRoleService.getAll();}
}
