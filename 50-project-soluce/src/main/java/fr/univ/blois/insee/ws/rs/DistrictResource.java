package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.services.DistrictService;
import fr.univ.blois.insee.services.exception.DistrictNotFoundExcetion;
import fr.univ.blois.insee.ws.bean.DistrictDto;
import fr.univ.blois.insee.ws.bean.mapper.CityMapper;
import fr.univ.blois.insee.ws.bean.mapper.DistrictMapper;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author François Robert
 */
@Path("departements")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class DistrictResource implements DistrictMapper, CityMapper {

  /**
   * Injection du service métier propre aux département
   */
  @EJB
  private DistrictService districtService;

  /**
   * Renvoi le département indiqué par le numé INSEE inclu dans le path
   * @param inseeId numero INSEE
   * @return département
   * @throws DistrictNotFoundExcetion Si le département n'a pas été trouvé
   */
  @GET
  @Path("{id:[0-9]*}")
  public DistrictDto getDistrict(@PathParam("id") String inseeId) throws DistrictNotFoundExcetion {
    return getDistrictDto(districtService.getDistrictByInsee(inseeId));
  }

}
