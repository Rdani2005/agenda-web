package edu.danny.agendacontactos.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a User Entity
 *
 * @author Danny Sequeira
 * @link https://rdani2005.works
 * @version 1.0
 * @since 2023-03-06
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(name = "identification", length = 50)
    private String identification;
    @Column(name = "user_name", length = 50)
    private String name;
    @Column(name = "registered")
    private Date register_day;
    @Column(length = 50)
    private String email;
    @Column(length = 50, nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    // Defining a Many-to-One relation on SQL.
    // @see https://www.adictosaltrabajo.com/2020/04/02/hibernate-onetoone-onetomany-manytoone-y-manytomany/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<UserRole> roles = new HashSet<>();


    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    /**
     * Method to get all user Authorities
     * @return list of user authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Getting the user's username
     * @return login
     */
    @Override
    public String getUsername() {
        return this.login;
    }

    /**
     * Account non expired?
     * @return Account expired or not
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * User account Locked.
     * @return account locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Validate credentials are enable
     * @return true or false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is user active?
     * @return true or false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
