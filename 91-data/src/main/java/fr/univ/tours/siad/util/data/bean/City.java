package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;

import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by francois on 12/09/15.
 */
@Entity
@SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = City.COUNT, query = "select count(c) from City c")
        , @NamedQuery(name = City.FIND_ALL, query = "select c from City c")
        , @NamedQuery(name = City.ELEVATION_SUM_BY_DISTRICT, query = "select sum(c.elevation) from City c where c.district.inseeId = :" + District.INSEEID)
        , @NamedQuery(name = City.FIND_BY_DISTRICT, query = "select c from City c where c.district.inseeId = :" + District.INSEEID)
        , @NamedQuery(name = City.FIND_CITY_BY_STATUS_FOR_DISTRICT, query = "select c from City c where c.district.inseeId = :" + District.INSEEID + " and c.cityStatus.label = :" + CityStatus.CITY_LABEL)
        , @NamedQuery(name = City.FIND_BY_INSEE, query = "select c from City c where c.inseeId = :" + City.INSEEID)
})
public class City {

    /**
     * Requete renvoyant le nombre de ville
     */
    public static final String COUNT = "City.COUNT";
    /**
     * Requete renvoyant toutes les villes
     */
    public static final String FIND_ALL = "City.FIND_ALL";
    /**
     * Requete renvoyant la somme des élévations par département (N° INSEE)
     */
    public static final String ELEVATION_SUM_BY_DISTRICT = "City.ELEVATION_SUM_BY_DISTRICT";
    /**
     * Requete renvoyant les villes d'un département (N° INSEE)
     */
    public static final String FIND_BY_DISTRICT = "City.FIND_BY_DISTRICT";
    /**
     * Requete renvoyant la liste des ville d'un certains status pour un département (N° INSEE)
     */
    public static final String FIND_CITY_BY_STATUS_FOR_DISTRICT = "City.FIND_CITY_BY_STATUS_FOR_DISTRICT";

    /**
     * Requete revoyant la liste des ville lié à ce numero INSEE - c'est un numero unique !
     */
    public static final String FIND_BY_INSEE = "City.FIND_BY_INSEE";
    
    public static final String CITY_ID = "id";
    public static final String INSEEID = "CITY_INSEEID";

    /**
     * Identifiant (PK)
     */
    @Id @GeneratedValue(strategy = SEQUENCE, generator = "city_sequence")
    private Long id;

    /**
     * Region
     */
    @ManyToOne(fetch = LAZY)
    private Region region;

    /**
     * Département
     */
    @ManyToOne(fetch = LAZY)
    private District district;

    /**
     * N° INSEE de la ville
     */
    @Column(length = 5)
    private String inseeId;

    /**
     * Nom de la ville
     */
    @Column(length = 100)
    private String name;

    /**
     * Status de la ville
     */
    @ManyToOne(fetch = EAGER)
    private CityStatus cityStatus;

    /**
     * Altitude
     */
    @Column
    private Double elevation;

    /**
     * Liste des codes de la ville
     */
    @OneToMany(mappedBy = "city", fetch = LAZY)
    private Set<ZipCode> zipCodeSet;

    /**
     * Constructeur
     */
    public City() {
    }

    public City(String[] cityAsStringArray, Region region, District district, CityStatus cityStatus) {
        this.setRegion(region);
        this.setDistrict(district);
        this.setCityStatus(cityStatus);
        this.setInseeId(cityAsStringArray[0]);
        this.setName(cityAsStringArray[2]);
        this.setElevation(Double.valueOf(cityAsStringArray[6]));
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<ZipCode> getZipCodeSet() {
        return zipCodeSet;
    }

    public void setZipCodeSet(Set<ZipCode> zipCodeSet) {
        this.zipCodeSet = zipCodeSet;
    }

    public CityStatus getCityStatus() {
        return cityStatus;
    }

    public void setCityStatus(CityStatus cityStatus) {
        this.cityStatus = cityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(region, city.region) &&
                Objects.equals(district, city.district) &&
                Objects.equals(inseeId, city.inseeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, district, inseeId);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", inseeId='" + inseeId + '\'' +
                ", id=" + id +
                ", district=" + district +
                '}';
    }
}