package fr.univ.blois.insee.ws.rs.Exception.mapper;

import fr.univ.blois.insee.services.exception.NoDsitrictFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class NoDsitrictFoundExceptionMapper implements ExceptionMapper<NoDsitrictFoundException> {
  @Override
  public Response toResponse(NoDsitrictFoundException exception) {
    return Response.status(404).entity(exception.getMessage()).build();
  }
}
