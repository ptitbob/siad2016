package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement(name = "personne")
@XmlAccessorType(FIELD)
public class PersonDto implements Serializable {

  @XmlAttribute(name = "reference")
  private String reference;

  @XmlElement(name = "nom")
  private String name;
  private AddressDto address;

  public PersonDto() {
  }

  public PersonDto(String reference, String name) {
    this();
    this.reference = reference;
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setAddress(AddressDto address) {
    this.address = address;
  }

  public AddressDto getAddress() {
    return address;
  }
}
