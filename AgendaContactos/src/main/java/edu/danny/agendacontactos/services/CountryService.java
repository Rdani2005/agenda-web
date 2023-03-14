package edu.danny.agendacontactos.services;

import edu.danny.agendacontactos.models.Country;
import edu.danny.agendacontactos.repositories.CountryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service class for managing Country objects.
 *
 * @author Danny Sequeira
 * @since 2023-03-06
 */
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    
    /**
     * Function that gets all countries from DB.
     * @return Array list with countries
     */
    public ArrayList<Country> getAllCountries() {
        return (ArrayList<Country>) countryRepository.findAll();
    }

    /**
     * Creates a new country on DB.
     * @param country the new country to be added.
     * @return The new country with all its info
     */
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    /**
     * Get a single country from db.
     * @param id used to filter the country
     * @return A country object from db.
     */
    public Optional<Country> getById(Long id) {
        return countryRepository.findById(id);
    }
}
