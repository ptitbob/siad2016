package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CityDetailDto extends CityDto {

  @XmlElement(name = "altitude")
  private Double elevation;

  @XmlElementWrapper(name = "Code-postaux")
  @XmlElement(name = "Code-postal")
  private List<ZipCodeDto> zipCodeList;

  public void setElevation(Double elevation) {
    this.elevation = elevation;
  }

  public Double getElevation() {
    return elevation;
  }

  public void setZipCodeList(List<ZipCodeDto> zipCodeList) {
    this.zipCodeList = zipCodeList;
  }

  public List<ZipCodeDto> getZipCodeList() {
    return zipCodeList;
  }

}
