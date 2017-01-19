package fr.univ.blois.insee.ws.rs.exception.mapper;

import fr.univ.blois.insee.services.exception.DistrictNotFoundExcetion;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class DistrictNotFoundExcetionMapper implements ExceptionMapper<DistrictNotFoundExcetion> {
  @Override
  public Response toResponse(DistrictNotFoundExcetion exception) {
    return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
  }
}
