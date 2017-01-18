package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement(name = "ville")
@XmlAccessorType(FIELD)
public class CityDto implements Serializable {

  private static final long serialVersionUID = 5700846312375495124L;

  @XmlAttribute(name = "insee")
  private String inseeId;

  @XmlElement(name = "nom")
  private String name;

  @XmlAttribute(name = "statut")
  private String cityStatus;

  @XmlElementWrapper(name = "code_postaux")
  @XmlElement(name = "code_postal")
  private List<String> zipcodeList;

  public CityDto() {
  }

  public CityDto(String inseeId, String name) {
    this();
    this.inseeId = inseeId;
    this.name = name;
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

  public String getCityStatus() {
    return cityStatus;
  }

  public void setCityStatus(String cityStatus) {
    this.cityStatus = cityStatus;
  }

  public List<String> getZipcodeList() {
    return zipcodeList;
  }

  public void setZipcodeList(List<String> zipcodeList) {
    this.zipcodeList = zipcodeList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CityDto)) return false;
    CityDto cityDto = (CityDto) o;
    return Objects.equals(getInseeId(), cityDto.getInseeId()) &&
        Objects.equals(getName(), cityDto.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInseeId(), getName());
  }

  @Override
  public String toString() {
    return "CityDto{" +
        "inseeId='" + inseeId + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

}
