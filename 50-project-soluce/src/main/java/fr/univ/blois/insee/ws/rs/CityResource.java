package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.services.CityService;
import fr.univ.blois.insee.services.exception.CityNotFoundException;
import fr.univ.blois.insee.ws.bean.CityDto;
import fr.univ.blois.insee.ws.bean.mapper.CityMapper;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author François Robert
 */
@Path("villes")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CityResource implements CityMapper {

  @EJB
  private CityService cityService;

  /**
   * Renvoi les détails d'une ville selon son n° INSEE
   *
   * @param cityInsee n° INSEE
   * @return ville
   * @throws CityNotFoundException si la ville n'a pas été trouvé
   */
  @GET
  @Path("{insee:[0-9]*}")
  public CityDto getCityByInsee(@PathParam("insee") String cityInsee) throws CityNotFoundException {
    return getCityDto(cityService.getCityByInsee(cityInsee));
  }

}
