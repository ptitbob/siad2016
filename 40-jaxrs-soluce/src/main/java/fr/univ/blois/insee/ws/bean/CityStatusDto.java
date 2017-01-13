package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CityStatusDto {
  @XmlAttribute(name = "type")
  private String status;

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
