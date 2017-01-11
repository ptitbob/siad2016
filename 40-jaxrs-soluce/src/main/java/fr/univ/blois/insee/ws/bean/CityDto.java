package fr.univ.blois.insee.ws.bean;

import fr.univ.blois.insee.model.CityStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CityDto implements Serializable {

  private static final long serialVersionUID = 4574432633969336139L;

  @XmlAttribute
  private Long id;

  @XmlElement(name = "INSEE")
  private String inseeId;

  @XmlElement(name = "nom")
  private String name;

  @XmlElement(name = "Statut")
  private CityStatusDto cityStatus;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setInseeId(String inseeId) {
    this.inseeId = inseeId;
  }

  public String getInseeId() {
    return inseeId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setCityStatus(CityStatusDto cityStatus) {
    this.cityStatus = cityStatus;
  }

  public CityStatusDto getCityStatus() {
    return cityStatus;
  }
}
