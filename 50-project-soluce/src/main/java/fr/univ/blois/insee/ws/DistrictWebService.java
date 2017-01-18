package fr.univ.blois.insee.ws;

import fr.univ.blois.insee.services.CityService;
import fr.univ.blois.insee.ws.bean.DistrictCityListDto;
import fr.univ.blois.insee.ws.bean.mapper.CityMapper;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.stream.Collectors;

/**
 *
 * le WSDL est accessible : http://localhost:8080/siad/SiadDistrictService?wsdl
 *
 * @author François Robert
 */
@WebService(serviceName = "SiadDistrictService", portName = "SiadDistrict")
public class DistrictWebService implements CityMapper {

  @EJB
  private CityService cityService;

  /**
   * Renvoi la liste des régions pou un départements
   * @param districtInseeId n° INSEE du département
   * @return liste des villes d'un département
   */
  @WebMethod(operationName = "villes")
  public DistrictCityListDto getCityListFromDistrict(String districtInseeId) {
    DistrictCityListDto districtCityListDto = new DistrictCityListDto(districtInseeId);
    districtCityListDto.setCityDtoList(
        cityService.getCityListForDistrict(districtInseeId)
        .stream()
        .map(this::getCityDto)
        .collect(Collectors.toList())
    );
    return districtCityListDto;
  }
}
