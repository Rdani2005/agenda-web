
package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.models.ContactType;
import edu.danny.agendacontactos.services.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * The ContactTypeController class is responsible for handling HTTP requests related to contact types.
 * It retrieves data from the ContactTypeService to perform CRUD operations on ContactType objects.
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/contact-type")
public class ContactTypeController {
    @Autowired
    ContactTypeService contactTypeService;

    /**
     * Retrieves all contact types.
     *
     * @return an ArrayList of ContactType objects.
     */
    @GetMapping()
    public ArrayList<ContactType> getAll() {
        return contactTypeService.getAllTypes();
    }

    /**
     * Adds a new contact type.
     *
     * @param type a ContactType object containing the data of the new contact type.
     * @return the new ContactType object.
     */
    @PostMapping()
    public ContactType addType(@RequestBody ContactType type) {
        return contactTypeService.addType(type);
    }
}
