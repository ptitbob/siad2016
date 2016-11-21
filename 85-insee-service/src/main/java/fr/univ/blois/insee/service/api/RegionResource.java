package fr.univ.blois.insee.service.api;

import fr.univ.blois.insee.service.bean.BeanMapper;
import fr.univ.blois.insee.service.bean.RegionDetailDto;
import fr.univ.blois.insee.service.bean.RegionDto;
import fr.univ.blois.insee.service.services.RegionService;
import fr.univ.blois.insee.service.services.exception.NoRegionFoundException;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
@Path("regions")
public class RegionResource {

  @Inject
  private BeanMapper beanMapper;

  @EJB
  private RegionService regionService;

  /**
   * Liste des régions
   * @return liste de régions
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<RegionDto> getRegionList() {
    return regionService.getRegionList().stream().map(beanMapper :: getRegionDto).collect(Collectors.toList());
  }

  /**
   * Renvoi le detail d'une région
   * @param regionInseeId n° Insee de la région
   * @return region
   * @throws NoRegionFoundException si la région n'est pas localisé
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id:[A-Z0-9]{2}}") // utilisation d'une regex
  public RegionDetailDto getRegionByInseeId(@PathParam("id") String regionInseeId) throws NoRegionFoundException {
    return beanMapper.getRegionDetailDto(regionService.getRegionByInseeId(regionInseeId));
  }

}
