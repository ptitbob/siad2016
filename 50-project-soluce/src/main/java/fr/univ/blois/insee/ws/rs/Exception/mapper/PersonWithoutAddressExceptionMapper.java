package fr.univ.blois.insee.ws.rs.Exception.mapper;

import fr.univ.blois.insee.ws.rs.Exception.PersonWithoutAddressException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;

/**
 * @author François Robert
 */
@Provider
public class PersonWithoutAddressExceptionMapper implements ExceptionMapper<PersonWithoutAddressException> {
  @Override
  public Response toResponse(PersonWithoutAddressException exception) {
    return Response.status(580).type(TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
  }
}
