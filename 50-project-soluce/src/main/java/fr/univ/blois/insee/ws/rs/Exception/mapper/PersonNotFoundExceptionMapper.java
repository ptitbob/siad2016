package fr.univ.blois.insee.ws.rs.Exception.mapper;

import fr.univ.blois.insee.services.exception.PersonNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {
  @Override
  public Response toResponse(PersonNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND).entity("Personne reference " + exception.getPersonReference() + " non trouvé").build();
  }
}
