package fr.univ.tours.siad.util.data.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "region")
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
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_sequence")
  @Column(name = "region_id")
  private Long id;

  /**
   * N° INSEE de la region
   */
  @Column(name = "inseeid", length = 2)
  private String inseeId;

  @OneToOne
  @JoinColumn(name = "cheflieuid", referencedColumnName = "inseeid")
  private City chefLieu;

  /**
   * Nom en majuscule
   */
  @Column(name = "uppername", length = 100)
  private String upperName;

  /**
   * Nom en formatage normal
   */
  @Column(name = "name", length = 100)
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public City getChefLieu() {
    return chefLieu;
  }

  public void setChefLieu(City chefLieu) {
    this.chefLieu = chefLieu;
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
