package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.ZipCode;
import fr.univ.blois.insee.ws.bean.ZipCodeDto;

/**
 * @author François Robert
 */
public interface ZipCodeMapper {

  default ZipCodeDto getZipCodeDto(ZipCode zipCode) {
    ZipCodeDto zipCodeDto = new ZipCodeDto();
    zipCodeDto.setZipCode(zipCode.getZipCode());
    return zipCodeDto;
  }
}
