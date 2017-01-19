package fr.univ.blois.insee.ws.rs.exception.mapper;

import fr.univ.blois.insee.ws.rs.exception.StatisticQueryException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class StatisticQueryExceptionMapper implements ExceptionMapper<StatisticQueryException> {

  @Override
  public Response toResponse(StatisticQueryException exception) {
    return Response.status(Response.Status.PRECONDITION_FAILED)
        .type(MediaType.TEXT_PLAIN_TYPE)
        .entity("Vous devez renseigner l'INSEE soit de la Région, Département ou ville")
        .build();
  }

}
