package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@SequenceGenerator(name = "region_sequence", sequenceName = "region_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = Region.FIND_ALL, query = "select r from Region r")
        , @NamedQuery(name = Region.COUNT, query = "select count(r) from Region r")
        , @NamedQuery(name = Region.FIND_BY_INSEEID, query = "select r from Region r where r.inseeId = :" + Region.INSEEID)
})
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
     * Liste des départements
     */
    @OneToMany(fetch = LAZY, mappedBy = "region")
    private Set<District> districtSet;

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

    public Set<District> getDistrictSet() {
        return districtSet;
    }

    public void setDistrictSet(Set<District> districtSet) {
        this.districtSet = districtSet;
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
