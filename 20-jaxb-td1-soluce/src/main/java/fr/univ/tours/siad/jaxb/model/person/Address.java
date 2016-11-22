package fr.univ.tours.siad.jaxb.model.person;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Adresse
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    /**
     * Identifiant
     */
    @XmlAttribute(name = "identifiant")
    public Long id;

    /**
     * Liste des ligne d'adresse (rue, étage, etc...)
     */
    @XmlElement(name = "Ligne")
    public List<String> lineList;

    /**
     * Code postal
     */
    @XmlElement(name = "code_postal")
    public String zipcode;

    /**
     * Ville
     */
    @XmlElement(name = "ville")
    public String town;

    public Address() {
    }

    public Address(Long id, String town, String zipcode, String... Adresses) {
        this.id = id;
        this.lineList = Arrays.asList(Adresses);
        this.town = town;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getLineList() {
        return lineList;
    }

    public void setLineList(List<String> lineList) {
        this.lineList = lineList;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) &&
                Objects.equals(getLineList(), address.getLineList()) &&
                Objects.equals(getZipcode(), address.getZipcode()) &&
                Objects.equals(getTown(), address.getTown());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getZipcode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", lineList=" + lineList +
                ", zipcode='" + zipcode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
