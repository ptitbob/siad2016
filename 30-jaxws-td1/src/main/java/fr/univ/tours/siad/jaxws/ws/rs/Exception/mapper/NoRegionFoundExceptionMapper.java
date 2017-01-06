package fr.univ.tours.siad.jaxws.ws.rs.Exception.mapper;

import fr.univ.tours.siad.jaxws.services.exception.NoRegionFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author François Robert
 */
@Provider
public class NoRegionFoundExceptionMapper implements ExceptionMapper<NoRegionFoundException> {

  @Context
  private HttpServletRequest httpServletRequest;

  @Override
  public Response toResponse(NoRegionFoundException exception) {
    if (httpServletRequest.getMethod().equals("DELETE")) {
      // en cas de deletion, une region non trouvé n'est pas une erreur du service
      // La deletion est idempotente
      return Response.ok().entity("Region deja effacee").type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).encoding("utf8").entity(exception.getMessage()).build();
  }
}
