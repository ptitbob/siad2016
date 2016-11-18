package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by francois on 12/09/15.
 */
@Entity
@SequenceGenerator(name = "citystatus_sequence", sequenceName = "citystatus_sequence", allocationSize = 1)
public class CityStatus {

    public static final String CITY_LABEL = "cityLabel";

    /**
     * Identifiant (PK)
     */
    @Id @GeneratedValue(strategy = SEQUENCE, generator = "citystatus_sequence")
    private Long id;

    /**
     * Libellé du statut de la ville
     */
    @Column(length = 100)
    private String label;

    /**
     * Constructeur
     */
    public CityStatus() {
    }

    public CityStatus(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CityStatus{" +
                "label='" + label + '\'' +
                '}';
    }
}
