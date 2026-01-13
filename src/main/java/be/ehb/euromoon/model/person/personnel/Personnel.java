package be.ehb.euromoon.model.person.personnel;

import be.ehb.euromoon.model.person.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing personnel working in the Euromoon railway system.
 *
 * <p>Personnel members are employees who operate trains and assist passengers.
 * All personnel have certifications that qualify them for their specific roles.
 * This class is extensible to allow for new types of personnel to be added
 * in the future.
 */
public abstract class Personnel extends Person {
    private List<String> certifications;

    public Personnel(String name, String lastname, String rijksregisternummer, LocalDate geboortedatum) {
        super(name, lastname, rijksregisternummer, geboortedatum);
        this.certifications = new ArrayList<>();
    }

    /**
     * Adds a certification to this personnel member.
     *
     * @param certification the certification to add
     */
    public void addCertification(String certification) {
        if (certification != null && !certification.trim().isEmpty()) {
            certifications.add(certification);
        }
    }

    /**
     * Checks if this personnel member has a specific certification.
     *
     * @param certification the certification to check for
     * @return true if the personnel has this certification, false otherwise
     */
    public boolean hasCertification(String certification) {
        return certifications.contains(certification);
    }

    public List<String> getCertifications() {
        return new ArrayList<>(certifications);
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = new ArrayList<>(certifications);
    }
}
