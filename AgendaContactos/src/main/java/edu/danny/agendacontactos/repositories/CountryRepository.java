package edu.danny.agendacontactos.repositories;

import edu.danny.agendacontactos.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Country info in BD
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}
