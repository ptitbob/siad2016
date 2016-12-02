package fr.univ.tours.siad.jaxws.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType(FIELD)
public class SimpleRegionDto {

  @XmlAttribute
  private String inseeId;

  @XmlElement(name = "nom")
  private String name;

  public SimpleRegionDto() {
  }

  public SimpleRegionDto(String inseeId, String name) {
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
}
