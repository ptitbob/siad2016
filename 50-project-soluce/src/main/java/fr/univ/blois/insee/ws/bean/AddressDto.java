package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement(name = "adresse")
@XmlAccessorType(FIELD)
public class AddressDto {

  @XmlAttribute
  private Long id;

  private Integer floor;

  private String line1;

  private String line2;

  private CityDto city;

  private String zipCode;


  public AddressDto(Long addressId, Integer floor, String line1, String line2) {
    setId(addressId);
    setFloor(floor);
    setLine1(line1);
    setLine2(line2);
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine1() {
    return line1;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public String getLine2() {
    return line2;
  }

  public void setCity(CityDto city) {
    this.city = city;
  }

  public CityDto getCity() {
    return city;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
