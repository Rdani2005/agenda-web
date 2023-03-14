package edu.danny.agendacontactos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a User Role Entity
 *
 * @author <a href="https://rdani2005.works">Danny Sequeira</a>
 *
 * @version 1.0
 * @since 2023-03-06
 */
@Getter
@Setter
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    int id;
    @Column(name = "role_name", length = 50)
    String name;
    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = {
            CascadeType.PERSIST,
            CascadeType.PERSIST,
            CascadeType.PERSIST,
            CascadeType.PERSIST})
    private List<User> contacts;
}
