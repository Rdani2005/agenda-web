
package edu.danny.agendacontactos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The ContactDto class represents a data transfer object for Contact objects.
 * It contains the necessary data for creating or updating a Contact object.
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-05
 */
@Getter
@Setter
public class ContactDto {

    private String identification;  // The identification of the contact.
    private String name;  // The name of the contact.
    private Date register_day;  // The date the contact was registered.
    private Date birth_day;  // The birth date of the contact.
    private String phone;  // The phone number of the contact.
    private String mobile;  // The mobile number of the contact.
    private String fax;  // The fax number of the contact.
    private String email;  // The email address of the contact.
    private long type_id;  // The ID of the ContactType associated with the contact.
    private long country_id;  // The ID of the Country associated with the contact.
}
