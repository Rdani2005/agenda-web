package edu.danny.agendacontactos.repositories;

import edu.danny.agendacontactos.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents a User Role Repository
 *
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-06
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
