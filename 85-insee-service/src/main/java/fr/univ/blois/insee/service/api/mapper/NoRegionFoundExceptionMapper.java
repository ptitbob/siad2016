package fr.univ.blois.insee.service.api.mapper;

import fr.univ.blois.insee.service.services.exception.NoRegionFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class NoRegionFoundExceptionMapper implements ExceptionMapper<NoRegionFoundException> {
  @Override
  public Response toResponse(NoRegionFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).encoding("utf8").entity(exception.getMessage()).build();
  }
}
