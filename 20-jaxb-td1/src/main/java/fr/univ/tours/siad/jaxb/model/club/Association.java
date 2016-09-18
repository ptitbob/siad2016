package fr.univ.tours.siad.jaxb.model.club;

import fr.univ.tours.siad.jaxb.model.person.Address;
import fr.univ.tours.siad.jaxb.model.person.Adherent;
import fr.univ.tours.siad.jaxb.model.person.Donor;
import fr.univ.tours.siad.jaxb.model.person.Person;

import java.util.*;

public class Association {

    /**
     * Identifiant
     */
    private Long id;

    /**
     * Nom de l'association
     */
    private String name;

    /**
     * Liste des personnes adhérant à l'association (peux aussi recevoir des donnateur)
     */
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
