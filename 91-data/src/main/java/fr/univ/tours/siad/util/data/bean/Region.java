package fr.univ.tours.siad.util.data.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "region_sequence", sequenceName = "region_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = Region.FIND_ALL, query = "select r from Region r")
        , @NamedQuery(name = Region.COUNT, query = "select count(r) from Region r")
        , @NamedQuery(name = Region.FIND_BY_INSEEID, query = "select r from Region r where r.inseeId = :" + Region.INSEEID)
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Region {

    /**
     * Renvoi la liste de toutes les région
     */
    public static final String FIND_ALL = "Region.FIND_ALL";
    /**
     * Renvoi le nombre de région
     */
    public static final String COUNT = "Region.COUNT";
    
    public static final String INSEEID = "regionInseeId";
    public static final String FIND_BY_INSEEID = "Region.FIND_BY_INSEEID";

    /**
     * Identifiant (PK)
     */
    @XmlAttribute
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_sequence")
    @Column(name = "region_id")
    private Long id;

    /**
     * N° INSEE de la region
     */
    @Column(length = 2)
    private String inseeId;

    /**
     * N° INSEE de la prefecture de region
     */
    @Column(length = 5)
    private String chefLieuId;

    /**
     * Nom en majuscule
     */
    @Column(length = 100)
    private String upperName;

    /**
     * Nom en formatage normal
     */
    @Column(length = 100)
    private String name;

    /**
     * Constructeur
     */
    public Region() {
    }

    public Region(String... regionAsStringArray) {
        this.setInseeId(regionAsStringArray[0]);
        this.setChefLieuId(regionAsStringArray[1]);
        this.setName(regionAsStringArray[4]);
        this.setUpperName(regionAsStringArray[3]);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChefLieuId() {
        return chefLieuId;
    }

    public void setChefLieuId(String dhefLieuId) {
        this.chefLieuId = dhefLieuId;
    }

    public String getInseeId() {
        return inseeId;
    }

    public void setInseeId(String inseeId) {
        this.inseeId = inseeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
                Objects.equals(inseeId, region.inseeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inseeId);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", inseeId='" + inseeId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
