package fr.univ.blois.insee.ws.rs.Exception.mapper;

import fr.univ.blois.insee.services.exception.NoCityFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class NoCityFoundExceptionMapper implements ExceptionMapper<NoCityFoundException> {
  @Override
  public Response toResponse(NoCityFoundException exception) {
    return Response.status(404).entity(exception.getMessage()).build();
  }
}
