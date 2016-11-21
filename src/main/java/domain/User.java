package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/**
 * A user of the CaveatEmptor auction application.
 *
 * @author Christian Bauer
 */
    @Entity
    @Table(name = "USER")
    public class User implements Serializable {

        @Id
        private Long id = null;
        private String firstname;
        private String lastname;
        private String username; // Unique and immutable

    /**
     * No-arg constructor for JavaBean tools
     */
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
