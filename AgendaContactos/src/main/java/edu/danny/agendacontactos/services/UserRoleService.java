package edu.danny.agendacontactos.services;

import edu.danny.agendacontactos.models.UserRole;
import edu.danny.agendacontactos.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * Service class for managing User Role objects.
 *
 * @author Danny Sequeira
 * @since 2023-03-06
 */
@Service
@RequiredArgsConstructor
public class UserRoleService {
    
    private final UserRoleRepository userRoleRepository;

    public ArrayList<UserRole> getAll() {return (ArrayList<UserRole>) userRoleRepository.findAll();}

    /**
     * Getting a single user role by its id.
     * @param id used as a filter
     * @return Single user Role from DB.
     */
    public UserRole getById(Long id) {
        return userRoleRepository.findById(id).get();
    }


    /**
     * Used to add a user role into the DB.
     * @param userRole Contains user role data.
     * @return A new user role.
     */
    public UserRole add(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
