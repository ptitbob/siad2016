package fr.univ.blois.insee.ws.rs.Exception.mapper;

import fr.univ.blois.insee.ws.rs.Exception.UnconsistantRegionDistrictRelation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class UnconsistantRegionDistrictRelationMapper implements ExceptionMapper<UnconsistantRegionDistrictRelation> {
  @Override
  public Response toResponse(UnconsistantRegionDistrictRelation exception) {
    return Response.status(412).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
  }
}
