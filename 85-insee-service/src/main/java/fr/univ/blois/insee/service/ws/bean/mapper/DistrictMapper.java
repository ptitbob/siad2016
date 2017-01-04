package fr.univ.blois.insee.service.ws.bean.mapper;

import fr.univ.blois.insee.service.ws.bean.DistrictDto;
import fr.univ.tours.siad.util.data.bean.District;

import javax.enterprise.context.RequestScoped;

/**
 * @author François Robert
 */
public interface DistrictMapper {

  default DistrictDto getDistrictDto(District district) {
    return new DistrictDto(district.getId(), district.getInseeId(), district.getChefLieuId(), district.getUpperName());
  }

}
