package fr.univ.tours.siad.jaxb.model.club;

import fr.univ.tours.siad.jaxb.model.person.*;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "association")
@XmlAccessorType(XmlAccessType.FIELD)
public class Association {

    /**
     * Identifiant
     */
    @XmlAttribute(name = "identifiant")
    private Long id;

    /**
     * Nom de l'association
     */
    @XmlElement(name = "nom")
    private String name;

    /**
     * Liste des personnes adhérant à l'association (peux aussi recevoir des donnateur)
     */
    @XmlElementWrapper(name = "membres")
    @XmlElements({
            @XmlElement(name = "membre_bureau", type = BoardMember.class)
            , @XmlElement(name = "adherent", type = Adherent.class)
            , @XmlElement(name = "donnateur", type = Donor.class)
    })
    private List<Person> adherentList;

    /**
     * Nombre d'adhérent
     */
    private int adherentCount = Integer.MIN_VALUE;

    /**
     * Adresse de l'association
     */
    private Address address;

    public Association() {
        this.adherentList = new LinkedList<>();
        adherentCount = 0;
    }

    public Association(Long id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getAdherentList() {
        return adherentList;
    }

    public void setAdherentList(List<Person> adherentList) {
        this.adherentList = adherentList;
    }

    public void addDonor(Donor donor) {
        adherentList.add(donor);
    }

    public void addAdherent(Adherent adherent) {
        adherentList.add(adherent);
        adherentCount++;
    }

    public int getAdherentCount() {
        return adherentCount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Association)) return false;
        Association that = (Association) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Association{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
