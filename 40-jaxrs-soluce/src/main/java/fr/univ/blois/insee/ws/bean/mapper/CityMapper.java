package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.ws.bean.CityDetailDto;
import fr.univ.blois.insee.ws.bean.CityDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
public interface CityMapper extends ZipCodeMapper, CityStatusMapper {

  /**
   * Renvoi les détails d'une ville (DTO)
   * @param city ville
   * @return détails d'une ville (DTO)
   */
  default CityDetailDto getCityDetailDto(City city) {
    CityDetailDto cityDetailDto = new CityDetailDto();
    cityDetailDto.setId(city.getId());
    cityDetailDto.setInseeId(city.getInseeId());
    cityDetailDto.setName(city.getName());
    cityDetailDto.setCityStatus(getCityStatusDto(city.getCityStatus()));
    cityDetailDto.setElevation(city.getElevation());
    cityDetailDto.setZipCodeList(
        city
            .getZipCodeSet()
            .stream()
            .map(this :: getZipCodeDto)
        .collect(Collectors.toList())
    );
    return cityDetailDto;
  }

  default CityDto getCityDto(City city) {
    CityDto cityDto = new CityDto();
    cityDto.setId(city.getId());
    cityDto.setInseeId(city.getInseeId());
    cityDto.setName(city.getName());
    cityDto.setCityStatus(getCityStatusDto(city.getCityStatus()));
    return cityDto;
  }
}
