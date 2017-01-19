package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.model.Region;
import fr.univ.blois.insee.services.DistrictService;
import fr.univ.blois.insee.services.RegionService;
import fr.univ.blois.insee.services.exception.NoRegionFoundException;
import fr.univ.blois.insee.ws.bean.DistrictDto;
import fr.univ.blois.insee.ws.bean.RegionDetailDto;
import fr.univ.blois.insee.ws.bean.RegionDto;
import fr.univ.blois.insee.ws.bean.mapper.DistrictMapper;
import fr.univ.blois.insee.ws.bean.mapper.RegionMapper;
import fr.univ.blois.insee.ws.rs.exception.IllegalInseeIdPresentationException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
@Path("regions")
public class RegionResource implements RegionMapper, DistrictMapper {

  @EJB
  private RegionService regionService;

  @EJB
  private DistrictService districtService;

  /**
   * Liste des régions
   * @return liste de régions
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<RegionDto> getRegionList() {
    return regionService.getRegionList().stream().map(this :: getRegionDto).collect(Collectors.toList());
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
    return getRegionDetailDto(regionService.getRegionByInseeId(regionInseeId));
  }

  /**
   * Renvoi la liste des département d'une région
   * @param regionInseeId n° INSEE de la région
   * @param districtId n° INSEE du département (REST)
   * @return liste des départements
   * @throws NoRegionFoundException si la région n'existe pas
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id:[A-Z0-9]{2}}/departements")
  public List<DistrictDto> getDistrictListForRegion(
      @PathParam("id") String regionInseeId,
      @QueryParam("departementInsee") @DefaultValue("N/A") String districtId
  ) throws NoRegionFoundException {
    // robert-f : j'ai volontairement decouper le code, il est possible de tout faire en une seule ligne de code.
    Region region = regionService.getRegionByInseeId(regionInseeId);
    List<DistrictDto> districtDtoList;
    if ("N/A".equals(districtId)) {
      districtDtoList = region.getDistrictSet()
          .stream().map(this :: getDistrictDto).collect(Collectors.toList());
    } else {
      districtDtoList = region
          .getDistrictSet().stream()
          .filter(district -> district.getInseeId().equals(districtId))
          .map(this :: getDistrictDto).collect(Collectors.toList());
    }
    return districtDtoList;
  }

  /**
   * Création d'une region en fournissant le nom complet et le nom normalisé
   * @param name nom complet
   * @param upperName nom normalisé
   * @return reponse
   */
  @POST
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @Produces({MediaType.WILDCARD, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response createRegion(@Context HttpHeaders httpHeaders, @FormParam("name") String name, @FormParam("upperName") String upperName) {
    Region region = regionService.createRegion(name, upperName);
    // Creation de l'URL de la ressource, accessible via une requête GET
    URI regionCreatedUri = UriBuilder
        .fromResource(RegionResource.class) // Pour générer les l'url de base du endpoint
        .path(region.getInseeId()) // Ajout d'un complément à l'URL, dans ce cas, le n° INSEE de la région
        .build(); // Génération, application du pattern Builder
    Response.ResponseBuilder responseBuilder = Response
        .created(regionCreatedUri);
    if (httpHeaders.getAcceptableMediaTypes().contains(MediaType.APPLICATION_JSON_TYPE) || httpHeaders.getAcceptableMediaTypes().contains(MediaType.APPLICATION_XML_TYPE)) {
      // Dans certains cas, deroger à la regle peut être utile...
      // Quand le service sert une app mobile succeptible d'avoir des latences reseau importante
      // Et que des donnée importante soient généré lors de la création, il peut être important de renvoyer la donnée crée dans la réponse et pas seulement l'URL de la ressource.
      responseBuilder.entity(region);
    }
    return responseBuilder.build();
  }

  /**
   * Mise à jour d'une région en ne prenant en compte que les champs nom, nom norlalisé et identifiant du chef lieu.
   * Les données inhérente au n° INSEE et Id ne sont pas mis à jour
   * L'id n° INSEE de l'URL et celui de la region sont vérifiés
   * @param inseeId N° INSEE fournit via l'URL
   * @param regionDetailDto Region (JSON, XML)
   * @return Ok si mis à jour sans problème.
   * @throws NoRegionFoundException si la region à mettre à jours n'existe pas.
   * @throws IllegalInseeIdPresentationException Le numéro insee fournit dans le path ne correspond pas à celi fourni dans le body
   */
  @PUT
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id:[A-Z0-9]{2}}") // utilisation d'une regex
  public Response updateRegion(@PathParam("id") String inseeId, RegionDetailDto regionDetailDto) throws NoRegionFoundException, IllegalInseeIdPresentationException {
    if (!inseeId.equals(regionDetailDto.getInseeId())) {
      // Vérification de la concordance entre N°INSEE de l'URL et celui des donnée updatée.
      throw new IllegalInseeIdPresentationException("Le numero INSEE d'identification ne correspond pas");
    }
    regionService.updateRegion(regionDetailDto.getInseeId(), regionDetailDto.getName(), regionDetailDto.getUpperName(), regionDetailDto.getChefLieuId());
    return Response.ok().build();
  }

  /**
   * Suppression d'une région
   * @param inseeId n° INSEE de la région a supprimer
   * @return reponse Ok, car gestion idempotent...—
   */
  @DELETE
  @Path("/{id:[A-Z0-9]{2}}") // utilisation d'une regex
  public Response deleteRegion(@PathParam("id") String inseeId) throws NoRegionFoundException {
    RegionDetailDto regionDetailDto = getRegionDetailDto(regionService.removeRegionById(inseeId));
    return Response.ok().build();
  }

}
