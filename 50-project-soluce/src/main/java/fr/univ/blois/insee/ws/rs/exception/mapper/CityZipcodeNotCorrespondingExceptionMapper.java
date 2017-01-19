package fr.univ.blois.insee.ws.rs.exception.mapper;

import fr.univ.blois.insee.ws.rs.exception.CityZipcodeNotCorrespondingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class CityZipcodeNotCorrespondingExceptionMapper implements ExceptionMapper<CityZipcodeNotCorrespondingException> {

  @Override
  public Response toResponse(CityZipcodeNotCorrespondingException exception) {
    return Response.status(Response.Status.PRECONDITION_FAILED).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
  }

}
