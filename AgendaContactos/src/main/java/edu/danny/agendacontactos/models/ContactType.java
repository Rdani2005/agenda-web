package edu.danny.agendacontactos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity class representing a type of contact (e.g., work, personal, etc.).
 * This class is mapped to the "contact_type" table in the database.
 *
 * @author Danny Sequeira
 * @link https://rdani2005.works
 * @version 1.0
 * @since 2023-03-05
 */
@Getter
@Setter
@Entity
@Table(name = "contact_type")
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(name = "type_name", length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = {CascadeType.PERSIST, CascadeType.PERSIST,CascadeType.PERSIST, CascadeType.PERSIST})
    private List<Contact> contacts;
}
