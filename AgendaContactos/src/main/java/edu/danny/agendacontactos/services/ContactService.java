package edu.danny.agendacontactos.services;

import edu.danny.agendacontactos.models.Contact;
import edu.danny.agendacontactos.models.ContactType;
import edu.danny.agendacontactos.models.Country;
import edu.danny.agendacontactos.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service class for managing contacts
 *
 * @author Danny Sequeira
 * @since 2023-03-06
 */
@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    /**
     * Retrieves all contacts in the repository
     *
     * @return an ArrayList of Contact objects
     */
    public ArrayList<Contact> getAll() {
        return (ArrayList<Contact>) contactRepository.findAll();
    }

    /**
     * Saves a new Contact object to the repository
     *
     * @param contact the Contact object to be saved
     * @return the saved Contact object
     */
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Retrieves a Contact object by its ID
     *
     * @param id the ID of the Contact object to be retrieved
     * @return the Contact object with the specified ID
     */
    public Contact getById(Long id) {
        return contactRepository.findById(id).get();
    }

    /**
     * Retrieves all Contact objects of a specific ContactType
     *
     * @param type the ContactType to filter by
     * @return an ArrayList of Contact objects with the specified ContactType
     */
    public ArrayList<Contact> getAllByType(ContactType type) {
        return contactRepository.findByType(type);
    }

    /**
     * Retrieves all Contact objects of a specific Country
     *
     * @param country the Country to filter by
     * @return an ArrayList of Contact objects with the specified Country
     */
    public ArrayList<Contact> getAllByCountry(Country country) {
        return contactRepository.findByCountry(country);
    }
}