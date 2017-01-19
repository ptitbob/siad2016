package fr.univ.blois.insee.ws.rs.exception.mapper;

import fr.univ.blois.insee.services.exception.CityNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class CityNotFoundExceptionMapper implements ExceptionMapper<CityNotFoundException> {
  @Override
  public Response toResponse(CityNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
  }
}
