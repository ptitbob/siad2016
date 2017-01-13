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
public class ZipCodeDto {
  @XmlAttribute(name = "valeur")
  private String zipCode;

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getZipCode() {
    return zipCode;
  }
}
