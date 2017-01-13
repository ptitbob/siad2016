package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.model.District;
import fr.univ.blois.insee.model.Region;
import fr.univ.blois.insee.services.CityService;
import fr.univ.blois.insee.services.DistrictService;
import fr.univ.blois.insee.services.RegionService;
import fr.univ.blois.insee.services.exception.CityAlreadyExistException;
import fr.univ.blois.insee.services.exception.NoCityFoundException;
import fr.univ.blois.insee.services.exception.NoDsitrictFoundException;
import fr.univ.blois.insee.services.exception.NoRegionFoundException;
import fr.univ.blois.insee.ws.bean.CityDetailDto;
import fr.univ.blois.insee.ws.bean.CityDto;
import fr.univ.blois.insee.ws.bean.mapper.CityMapper;
import fr.univ.blois.insee.ws.rs.Exception.UnconsistantRegionDistrictRelation;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
@Path("villes")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CityResource implements CityMapper {

  private static final String DEFAULT_QUERY_VALUE = "&ToTo";
  public static final String NON_VALUE = "NON";
  public static final String OUI_VALUE = "OUI";

  @EJB
  private CityService cityService;

  @EJB
  private RegionService regionService;

  @EJB
  private DistrictService districtService;


  /**
   * Renvoi la liste des villes par Region, Département sinon vide
   *
   * @param regionInsee   n° INSEE de la région (prioritaire)
   * @param districtInsee n° INSEE du département
   * @return liste
   */
  @GET
  public List<CityDto> getCityList(
      @QueryParam("region") @DefaultValue(DEFAULT_QUERY_VALUE) String regionInsee
      , @QueryParam("departement") @DefaultValue(DEFAULT_QUERY_VALUE) String districtInsee
      , @QueryParam("chef-lieu") @DefaultValue(NON_VALUE) String onlyMainCity
  ) throws NoRegionFoundException, NoCityFoundException, NoDsitrictFoundException {
    List<City> cityList;
    boolean mainCity = OUI_VALUE.equals(onlyMainCity);
    if (mainCity) {
      List<CityDto> cityDtoList = new ArrayList<>();
      if (!DEFAULT_QUERY_VALUE.equals(regionInsee)) {
        Region region = regionService.getRegionByInseeId(regionInsee);
        cityDtoList.add(getCityDto(region.getChefLieu()));
      } else if (!DEFAULT_QUERY_VALUE.equals(districtInsee)) {
        District district = districtService.getDistrictbyInsee(districtInsee);
        cityDtoList.add(getCityDto(cityService.getCityByInsee(district.getChefLieuId())));
      }
      return cityDtoList;
    } else  if (!DEFAULT_QUERY_VALUE.equals(regionInsee)) {
      cityList = cityService.getRegionCityList(regionInsee);
    } else if (!DEFAULT_QUERY_VALUE.equals(districtInsee)) {
      cityList = cityService.getDistrictCityList(districtInsee);
    } else {
      cityList = new ArrayList<>();
    }
    return cityList
        .stream().map(this::getCityDto)
        .collect(Collectors.toList());
  }

  /**
   * Renvoi les détails d'une ville selon son n° INSEE
   *
   * @param cityInsee n° INSEE
   * @return ville
   * @throws NoCityFoundException si la ville n'a pas été trouvé
   */
  @GET
  @Path("{insee:[0-9]*}")
  public CityDetailDto getCityByInsee(@PathParam("insee") String cityInsee) throws NoCityFoundException {
    return getCityDetailDto(cityService.getCityByInsee(cityInsee));
  }

  /**
   * Crée un ville
   *
   * @param regionInsee   n° INSEE de la region de la ville
   * @param districtInsee n° INSEE du département de la ville
   * @param cityName      Nom de la ville
   * @param cityInsee     n° INSEE de la cille
   * @return Reponse contenant au niveau des header la location de la ressource ville crée (requete GET)
   * @throws CityAlreadyExistException Erreur la ville (code INSEE) existe déjà
   * @throws NoRegionFoundException Erreur, la region n'a pas été trouvé
   * @throws NoDsitrictFoundException Erreur, le departement n'a pas été trouvé
   */
  @POST
  public Response createCity(
      @FormParam("region") String regionInsee
      , @FormParam("departement") String districtInsee
      , @FormParam("nom") String cityName
      , @FormParam("insee") String cityInsee
  ) throws CityAlreadyExistException, NoRegionFoundException, NoDsitrictFoundException {
    Region region = regionService.getRegionByInseeId(regionInsee);
    District district = districtService.getDistrictbyInsee(districtInsee);
    if (district.getRegion().equals(region)) {
      City city = cityService.createCity(region, district, cityName, cityInsee);
      return buildCreatedCityResponse(city);
    } else {
      throw new UnconsistantRegionDistrictRelation();
    }
  }

  /**
   * Permet de construire la reponse pour une ville créée
   * @param city ville
   * @return Reponse contenant au niveau des header la location de la ressource ville crée (requete GET)
   */
  private Response buildCreatedCityResponse(City city) {
    URI cityCreatedUri = UriBuilder
        .fromResource(CityResource.class) // Pour générer les l'url de base du endpoint
        .path(city.getInseeId()) // Ajout d'un complément à l'URL, dans ce cas, le n° INSEE de la ville
        .build(); // Génération, application du pattern Builder
    return Response
        .created(cityCreatedUri)
        .build();
  }

  /**
   * Suppression d'une ville selon son n° INSEE
   * @param cityInsee n° INSEE de la ville
   * @return reponse OK (toujours)
   */
  @DELETE
  @Path("{insee:[0-9]*}")
  public Response deleteCity(@PathParam("insee") String cityInsee) {
    cityService.remove(cityInsee);
    return Response.ok().build();
  }

  /**
   * Ajout d'un code postal pour une ville
   * @param cityInsee n° INSEE de la ville
   * @param zipCode code postal a ajouter
   * @return Reponse contenant au niveau des header la location de la ressource ville (requete GET)
   * @throws NoCityFoundException Erreur, la ville n'a pas été trouvé
   */
  @PUT
  @Path("{insee:[0-9]*}")
  public Response addZipCode(
      @PathParam("insee") String cityInsee
      , @FormParam("code-postal") String zipCode
  ) throws NoCityFoundException {
    City city = cityService.getCityByInsee(cityInsee);
    cityService.addZipCode(city, zipCode);
    return buildCreatedCityResponse(city);
  }


}
