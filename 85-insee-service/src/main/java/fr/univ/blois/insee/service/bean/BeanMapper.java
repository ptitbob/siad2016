package fr.univ.blois.insee.service.bean;

import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.enterprise.context.RequestScoped;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
@RequestScoped
public class BeanMapper {

  public RegionDto getRegionDto(Region region) {
    return new RegionDto(region.getId(), region.getInseeId(), region.getChefLieuId(), region.getUpperName());
  }

  public RegionDetailDto getRegionDetailDto(Region region) {
    return new RegionDetailDto(region.getId(), region.getInseeId(), region.getChefLieuId(), region.getUpperName(), region.getName(), region.getDistrictSet().stream().map(this::getDsitrictDto).collect(Collectors.toSet()));
  }

  private DistrictDto getDsitrictDto(District district) {
    return new DistrictDto(district.getId(), district.getInseeId(), district.getChefLieuId(), district.getUpperName());
  }

}
