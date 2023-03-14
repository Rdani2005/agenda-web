package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.dto.ContactDto;
import edu.danny.agendacontactos.models.Contact;
import edu.danny.agendacontactos.services.ContactService;
import edu.danny.agendacontactos.services.ContactTypeService;
import edu.danny.agendacontactos.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * The ContactController class is responsible for handling HTTP requests related to contacts.
 * It retrieves data from the ContactService, ContactTypeService, and CountryService to perform
 * CRUD operations on Contact objects.
 * 
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @Autowired
    ContactTypeService contactTypeService;

    @Autowired
    CountryService countryService;

    /**
     * Retrieves all contacts.
     *
     * @return an ArrayList of Contact objects.
     */
    @GetMapping()
    public ArrayList<Contact> getContacts() {
        return contactService.getAll();
    }

    /**
     * Retrieves a contact by ID.
     *
     * @param id the ID of the contact to retrieve.
     * @return a Contact object.
     */
    @GetMapping(path = "/{id}")
    public Contact getById(@PathVariable("id") Long id) {
        return contactService.getById(id);
    }

    /**
     * Retrieves all contacts of a given contact type.
     *
     * @param type the ID of the contact type.
     * @return an ArrayList of Contact objects.
     */
    @GetMapping("/by-type")
    public ArrayList<Contact> getAllByCategory(@RequestParam("type") Long type) {
        return contactService.getAllByType(contactTypeService.getById(type).get());
    }

    /**
     * Retrieves all contacts from a given country.
     *
     * @param country the ID of the country.
     * @return an ArrayList of Contact objects.
     */
    @GetMapping("/by-country")
    public ArrayList<Contact> getAllByCountry(@RequestParam("country") Long country) {
        return contactService.getAllByCountry(countryService.getById(country).get());
    }

    /**
     * Adds a new contact.
     *
     * @param contactDto a ContactDto object containing the data of the new contact.
     * @return the new Contact object.
     */
    @PostMapping()
    public Contact addContact(@RequestBody ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setIdentification(contactDto.getIdentification());
        contact.setName(contactDto.getName());
        contact.setRegister_day(new Date());
        contact.setBirth_day(contactDto.getBirth_day());
        contact.setPhone(contactDto.getPhone());
        contact.setMobile(contactDto.getMobile());
        contact.setFax(contactDto.getFax());
        contact.setEmail(contactDto.getEmail());
        contact.setType(contactTypeService.getById(contactDto.getType_id()).get());
        contact.setCountry(countryService.getById(contactDto.getCountry_id()).get());
        return contactService.addContact(contact);
    }
}