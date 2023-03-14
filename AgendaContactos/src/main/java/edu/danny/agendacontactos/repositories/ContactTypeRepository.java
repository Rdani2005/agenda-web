package edu.danny.agendacontactos.repositories;

import edu.danny.agendacontactos.models.ContactType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Represents a Contact Type Repository
 *
 * @author Danny Sequeira
 * @link https://rdani2005.works
 * @version 1.0
 * @since 2023-03-06
 */
@Repository
public interface ContactTypeRepository extends CrudRepository<ContactType, Long> {
}
