package fr.univ.tours.siad.jaxws.ws;

import fr.univ.tours.siad.jaxws.services.RegionService;
import fr.univ.tours.siad.jaxws.ws.model.SimpleRegionDto;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
@WebService(serviceName = "SiadRegionService", portName = "SiadRegion")
public class RegionWebService {

  @EJB
  private RegionService regionService;


  @WebMethod(operationName = "region_liste")
  private List<SimpleRegionDto> getSimpleRegionDtoList() {
    return regionService
        .getRegionList()
        .stream()
        .map(
            this::getSimpleRegionDto
        ).collect(Collectors.toList());
  }

  private SimpleRegionDto getSimpleRegionDto(Region region) {
    return new SimpleRegionDto(region.getInseeId(), region.getName());
  }

}
