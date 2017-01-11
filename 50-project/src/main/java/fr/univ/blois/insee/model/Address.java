package fr.univ.blois.insee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by francois on 06/10/15.
 */
@Entity
@SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
public class Address implements Serializable {

    private static final long serialVersionUID = 7676953046933420988L;

    private static final String CITY_FK = "CITY_FK";
    private static final String ZIPCODE_FK = "ZIPCODE_FK";
    public static final String ADDRESS_ID = "ADDRESS_ID";

    /**
     * Identifiant (PK)
     */
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @Column(name = ADDRESS_ID)
    private Long id;

    /**
     * Ligne 1 de l'adresse
     */
    @Column(length = 100)
    private String line1;

    /**
     * Ligne 2 de l'adresse
     */
    @Column(length = 100)
    private String Line2;

    /**
     * étage
     */
    @Column @Min(0)
    private Integer floor;

    /**
     * Ville
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CITY_FK, referencedColumnName = City.CITY_ID)
    private City city;

    /**
     * Code postal
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ZIPCODE_FK, referencedColumnName = ZipCode.ZIPCODE_ID)
    private ZipCode zipCode;

    /**
     * Constructeur
     */
    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return Line2;
    }

    public void setLine2(String line2) {
        Line2 = line2;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }
}
