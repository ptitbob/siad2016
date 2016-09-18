package fr.univ.tours.siad.jaxb.model.person;

import java.util.Objects;

/**
 * Donateur
 */
public class Donor extends Person {

    /**
     * Montant de la donnation
     */
    private int donationAmount;

    private DonorType donorType;

    public Donor() {
    }

    public Donor(Long id, String name, String surname, int donationAmount, DonorType donorType) {
        this(id, name, surname, donationAmount, donorType, null);
    }

    public Donor(Long id, String name, String surname, int donationAmount, DonorType donorType, Address address) {
        super(id, name, surname, address);
        setDonationAmount(donationAmount);
        setDonorType(donorType);
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    public DonorType getDonorType() {
        return donorType;
    }

    public void setDonorType(DonorType donorType) {
        this.donorType = donorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donor)) return false;
        Donor donor = (Donor) o;
        return Objects.equals(getDonationAmount(), donor.getDonationAmount()) &&
                Objects.equals(getDonorType(), donor.getDonorType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDonorType());
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donationAmount=" + donationAmount +
                ", donorType=" + donorType +
                "} " + super.toString();
    }
}
