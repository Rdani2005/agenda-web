package edu.danny.agendacontactos.services;

import edu.danny.agendacontactos.models.ContactType;
import edu.danny.agendacontactos.repositories.ContactTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service class for managing ContactType objects.
 *
 * @author Danny Sequeira
 * @since 2023-03-06
 */
@Service
public class ContactTypeService {

    @Autowired
    ContactTypeRepository contactTypeRepository;

    /**
     * Get all ContactTypes.
     *
     * @return list of ContactType objects
     */
    public ArrayList<ContactType> getAllTypes() {
        return (ArrayList<ContactType>) contactTypeRepository.findAll();
    }

    /**
     * Add a new ContactType.
     *
     * @param type the ContactType object to be added
     * @return the newly added ContactType object
     */
    public ContactType addType(ContactType type) {
        return contactTypeRepository.save(type);
    }

    /**
     * Get a ContactType by its ID.
     *
     * @param id the ID of the ContactType to retrieve
     * @return an Optional containing the retrieved ContactType, or an empty Optional if no ContactType was found
     */
    public Optional<ContactType> getById(Long id) {
        return contactTypeRepository.findById(id);
    }
}