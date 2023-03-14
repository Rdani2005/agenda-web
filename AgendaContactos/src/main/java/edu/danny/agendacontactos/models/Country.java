package edu.danny.agendacontactos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a country.
 * Contains information about the country name and a list of contacts associated with it.
 *
 * @author Danny Sequeira
 * @link https://rdani2005.works
 * @version 1.0
 * @since 2023-03-05
 */
@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(name = "country_name", length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = {
            CascadeType.PERSIST,
            CascadeType.PERSIST,
            CascadeType.PERSIST,
            CascadeType.PERSIST})
    private List<Contact> contacts;
}