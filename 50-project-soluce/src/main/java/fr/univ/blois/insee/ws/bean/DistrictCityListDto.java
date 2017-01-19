package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement(name = "Departement_liste_villes")
@XmlAccessorType(FIELD)
public class DistrictCityListDto implements Serializable {

  private static final long serialVersionUID = -112038672859021423L;

  @XmlAttribute(name = "departement")
  private String districtInseeId;

  @XmlElementWrapper(name = "villes")
  @XmlElement(name = "ville")
  private List<CityDto> cityDtoList;

  public DistrictCityListDto() {
    cityDtoList = new ArrayList<>();
  }

  public DistrictCityListDto(String districtInseeId) {
    this();
    this.districtInseeId = districtInseeId;
  }

  public String getDistrictInseeId() {
    return districtInseeId;
  }

  public void setDistrictInseeId(String districtInseeId) {
    this.districtInseeId = districtInseeId;
  }

  public List<CityDto> getCityDtoList() {
    return cityDtoList;
  }

  public void setCityDtoList(List<CityDto> cityDtoList) {
    this.cityDtoList = cityDtoList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DistrictCityListDto)) return false;
    DistrictCityListDto that = (DistrictCityListDto) o;
    return Objects.equals(getDistrictInseeId(), that.getDistrictInseeId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDistrictInseeId());
  }

  @Override
  public String toString() {
    return "DistrictCityListDto{" +
        "districtInseeId='" + districtInseeId + '\'' +
        ", cityDtoList=" + cityDtoList +
        '}';
  }

}
