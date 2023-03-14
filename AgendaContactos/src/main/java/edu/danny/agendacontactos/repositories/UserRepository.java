package edu.danny.agendacontactos.repositories;

import edu.danny.agendacontactos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Represents a User Repository
 *
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-06
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByLogin(String login);
}
