package fr.univ.blois.insee.service.api;

import fr.univ.blois.insee.service.services.RegionService;
import fr.univ.tours.siad.util.data.bean.Region;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author François Robert
 */
@Path("regions")
public class RegionResource {

  @EJB
  private RegionService regionService;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Region> getRegionList() {
    return regionService.getRegionList();
  }

}
