
package edu.danny.agendacontactos.controllers;

import edu.danny.agendacontactos.models.Country;
import edu.danny.agendacontactos.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
/**
 * The CountryController class is responsible for handling HTTP requests related to countries.
 * It retrieves data from the CountryService to perform CRUD operations on Country objects.
 *  @author Danny Sequeira
 * @see https://rdani2005.works
 * @version 1.0
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    @Autowired
    CountryService countryService;

    /**
     * Retrieves all countries.
     *
     * @return an ArrayList of Country objects.
     */
    @GetMapping("/all")
    public ArrayList<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    /**
     * Adds a new country.
     *
     * @param country a Country object containing the data of the new country.
     * @return the new Country object.
     */
    @PostMapping("/add")
    public Country saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }
}