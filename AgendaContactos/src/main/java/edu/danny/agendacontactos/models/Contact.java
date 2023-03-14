package edu.danny.agendacontactos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a contact entity.
 * @author Danny Sequeira
 * @link https://rdani2005.works
 * @version 1.0
 * @since 2023-03-05
 */
@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id; // ID Primary key
    @Column(name = "identification", length = 50, nullable = false, unique = true)
    private String identification; // Identification
    @Column(name = "contact_name", length = 50)
    private String name; // name
    @Column(name = "registered")
    private Date register_day; // registered day
    @Column(name = "birth")
    private Date birth_day; // birthday
    @Column(length = 50)
    private String phone; // phone
    @Column(length = 50)
    private String mobile; // mobile
    @Column(length = 50)
    private String fax; // fax
    @Column(length = 50)
    private String email; // email
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST,CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "type_id")
    private ContactType type; // type
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST,CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "country_id")
    private Country country; // country
}
