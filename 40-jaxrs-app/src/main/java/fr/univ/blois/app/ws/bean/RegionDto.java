package fr.univ.blois.app.ws.bean;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType
public class RegionDto {

  @XmlAttribute
  private Long id;

  private String inseeId;

  private String name;

  public RegionDto() {
  }

  public RegionDto(Long id, String inseeId, String name) {
    this.id = id;
    this.inseeId = inseeId;
    this.name = name;
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
}
