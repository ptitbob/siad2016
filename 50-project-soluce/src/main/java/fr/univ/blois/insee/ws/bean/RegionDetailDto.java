package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * @author François Robert
 */
@XmlRootElement
public class RegionDetailDto extends RegionDto {

  private String name;

  @XmlElementWrapper(name = "Districts")
  @XmlElement(name = "district")
  private Set<DistrictDto> districtSet;

  public RegionDetailDto() {
  }

  public RegionDetailDto(Long id, String inseeId, String chefLieuId, String upperName, String name, Set<DistrictDto> districtSet) {
    super(id, inseeId, chefLieuId, upperName);
    this.name = name;
    this.districtSet = districtSet;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<DistrictDto> getDistrictSet() {
    return districtSet;
  }

  public void setDistrictSet(Set<DistrictDto> districtSet) {
    this.districtSet = districtSet;
  }
}
