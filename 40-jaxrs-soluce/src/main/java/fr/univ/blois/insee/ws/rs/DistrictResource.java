package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.model.District;
import fr.univ.blois.insee.services.CityService;
import fr.univ.blois.insee.services.DistrictService;
import fr.univ.blois.insee.services.exception.NoCityFoundException;
import fr.univ.blois.insee.services.exception.NoDsitrictFoundException;
import fr.univ.blois.insee.ws.bean.CityDetailDto;
import fr.univ.blois.insee.ws.bean.DistrictDto;
import fr.univ.blois.insee.ws.bean.mapper.CityMapper;
import fr.univ.blois.insee.ws.bean.mapper.DistrictMapper;

import javax.ejb.EJB;
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
@Path("departements")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class DistrictResource implements DistrictMapper, CityMapper {

  /**
   * Injection du service métier propre aux département
   */
  @EJB
  private DistrictService districtService;

  @EJB
  private CityService cityService;

  /**
   * renvoi la liste de tous les départements
   * @return liste
   */
  @GET
  public List<DistrictDto> getDistrictList() {
    return districtService.getDistrictList()
        .stream().map(this :: getDistrictDto)
        .collect(Collectors.toList());
  }

  /**
   * Renvoi le département indiqué par le numé INSEE inclu dans le path
   * @param inseeId numero INSEE
   * @return département
   * @throws NoDsitrictFoundException Si le département n'a pas été trouvé
   */
  @GET
  @Path("{id:[0-9]*}")
  public DistrictDto getDistrict(@PathParam("id") String inseeId) throws NoDsitrictFoundException {
    return getDistrictDto(districtService.getDistrictbyInsee(inseeId));
  }

  @GET
  @Path("{id:[0-9]*}/chef-lieu")
  public CityDetailDto getDistrictMainCity(@PathParam("id") String inseeId) throws NoDsitrictFoundException, NoCityFoundException {
    District district = districtService.getDistrictbyInsee(inseeId);
    return getCityDetailDto(cityService.getCityByInsee(district.getChefLieuId()));
  }

}
