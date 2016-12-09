package fr.univ.blois.app.ws.rs;

import fr.univ.blois.app.model.Region;
import fr.univ.blois.app.services.RegionService;
import fr.univ.blois.app.ws.bean.RegionDto;
import fr.univ.blois.app.ws.bean.converter.RegionConverter;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author François Robert
 */
@Path("region")
public class RegionEndpoint {

  @EJB
  private RegionService regionService;

  @GET
  public List<RegionDto> getRegionList() {
    RegionConverter regionConverter = new RegionConverter();
    List<Region> regionList = regionService.getRegionList();
    List<RegionDto> regionDtoList = new ArrayList<>();
    for (Region region : regionList) {
      regionDtoList.add(regionConverter.getDto(region));
    }
    return regionDtoList;
  }

}
