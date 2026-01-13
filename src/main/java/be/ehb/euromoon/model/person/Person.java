package be.ehb.euromoon.model.person;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Abstract base class representing a person in the Euromoon railway system.
 *
 * <p>This class provides the common attributes shared by all persons,
 * including passengers and personnel. It cannot be instantiated directly
 * as all persons must have a specific role.
 */
public abstract class Person {
    private String name;
    private String lastname;
    private String rijksregisternummer;
    private LocalDate geboortedatum;

    public Person(String name, String lastname, String rijksregisternummer, LocalDate geboortedatum) {
        this.name = name;
        this.lastname = lastname;
        this.rijksregisternummer = rijksregisternummer;
        this.geboortedatum = geboortedatum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(rijksregisternummer, person.rijksregisternummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rijksregisternummer);
    }

    @Override
    public String toString() {
        return name + " " + lastname + " (RRN: " + rijksregisternummer + ")";
    }
}
