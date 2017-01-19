package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.model.ZipCode;
import fr.univ.blois.insee.ws.bean.CityDto;

import java.util.stream.Collectors;

/**
 * @author François Robert
 */
public interface CityMapper {

  default CityDto getCityDto(City city) {
    CityDto cityDto = getCityDtoWithoutZipCode(city);
    if (city != null) {
      cityDto.setZipcodeList(city.getZipCodeSet()
          .stream()
          .map(ZipCode::getZipCode)
          .collect(Collectors.toList())
      );
    }
    return cityDto;
  }

  default CityDto getCityDtoWithoutZipCode(City city) {
    if (city == null) {
      return new CityDto();
    }
    CityDto cityDto = new CityDto(city.getInseeId(), city.getName());
    cityDto.setCityStatus(city.getCityStatus().getLabel());
    return cityDto;
  }
}
