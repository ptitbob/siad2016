package fr.univ.blois.insee.service.ws.bean.mapper;

import fr.univ.blois.insee.service.ws.bean.DistrictDto;
import fr.univ.blois.insee.service.ws.bean.RegionDetailDto;
import fr.univ.blois.insee.service.ws.bean.RegionDto;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.enterprise.context.RequestScoped;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
public interface RegionMapper extends DistrictMapper {

  default RegionDto getRegionDto(Region region) {
    return new RegionDto(region.getId(), region.getInseeId(), region.getChefLieu() == null ? "N/A" : region.getChefLieu().getInseeId(), region.getUpperName());
  }

  default RegionDetailDto getRegionDetailDto(Region region) {
    return new RegionDetailDto(region.getId(), region.getInseeId(), region.getChefLieu().getInseeId(), region.getUpperName(), region.getName(), region.getDistrictSet().stream().map(this :: getDistrictDto).collect(Collectors.toSet()));
  }

}