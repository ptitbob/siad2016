package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.CityStatus;
import fr.univ.blois.insee.ws.bean.CityStatusDto;

/**
 * @author François Robert
 */
public interface CityStatusMapper {

  default CityStatusDto getCityStatusDto(CityStatus cityStatus) {
    CityStatusDto cityStatusDto = new CityStatusDto();
    cityStatusDto.setStatus(cityStatus == null ? "N/A" : cityStatus.getLabel());
    return cityStatusDto;
  }
}
