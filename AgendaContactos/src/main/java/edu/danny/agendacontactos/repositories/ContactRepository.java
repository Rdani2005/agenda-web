package edu.danny.agendacontactos.repositories;

import edu.danny.agendacontactos.models.Contact;
import edu.danny.agendacontactos.models.ContactType;
import edu.danny.agendacontactos.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Represents a Contact Repository
 *
 * @author Danny Sequeira
 * @link https://rdani2005.works
 * @version 1.0
 * @since 2023-03-06
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    /**
     * Function that will find a list of contacts by its country.
     * @param country country that we will search
     * @return List of user from a specific country
     * @author Danny Sequeira
     */
    public abstract ArrayList<Contact> findByCountry(Country country);
    /**
     * Function that will find a list of contacts by its type.
     * @param contactType type that we will search
     * @return List of user from a specific type
     * @author Danny Sequeira
     */
    public abstract ArrayList<Contact> findByType(ContactType contactType);
}
