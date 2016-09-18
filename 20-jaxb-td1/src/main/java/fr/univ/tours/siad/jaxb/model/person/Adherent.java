package fr.univ.tours.siad.jaxb.model.person;

import java.util.Objects;

public class Adherent extends Person {

    /**
     * Date d'adhésion (au format YYYYMMDD)
     */
    private String membershipDate;

    public Adherent() {
    }

    public Adherent(Long id, String name, String surname, String membershipDate, Address address) {
        super(id, name, surname, address);
        this.membershipDate = membershipDate;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adherent)) return false;
        if (!super.equals(o)) return false;
        Adherent adherent = (Adherent) o;
        return Objects.equals(getMembershipDate(), adherent.getMembershipDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMembershipDate());
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "membershipDate='" + membershipDate + '\'' +
                "} " + super.toString();
    }
}
