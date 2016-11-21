package fr.univ.blois.insee.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType(FIELD)
public class DistrictDto {

  @XmlAttribute
  public Long id;

  /**
   * N° INSEE du département
   */
  private String inseeId;

  /**
   * Prefecture départementale
   */
  private String chefLieuId;

  /**
   * Nom en majuscule
   */
  private String upperName;

  public DistrictDto() {
  }

  public DistrictDto(Long id, String inseeId, String chefLieuId, String upperName) {
    this.id = id;
    this.inseeId = inseeId;
    this.chefLieuId = chefLieuId;
    this.upperName = upperName;
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

  public String getChefLieuId() {
    return chefLieuId;
  }

  public void setChefLieuId(String chefLieuId) {
    this.chefLieuId = chefLieuId;
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
    if (!(o instanceof DistrictDto)) return false;
    DistrictDto that = (DistrictDto) o;
    return Objects.equals(getInseeId(), that.getInseeId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInseeId());
  }

  @Override
  public String toString() {
    return "DistrictDto{" +
        "id=" + id +
        ", inseeId='" + inseeId + '\'' +
        ", chefLieuId='" + chefLieuId + '\'' +
        ", upperName='" + upperName + '\'' +
        '}';
  }
}
