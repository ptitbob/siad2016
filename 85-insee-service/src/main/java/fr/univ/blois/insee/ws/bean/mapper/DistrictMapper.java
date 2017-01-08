package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.ws.bean.DistrictDto;
import fr.univ.tours.siad.util.data.bean.District;

/**
 * @author François Robert
 */
public interface DistrictMapper {

  default DistrictDto getDistrictDto(District district) {
    return new DistrictDto(district.getId(), district.getInseeId(), district.getChefLieuId(), district.getUpperName());
  }

}
