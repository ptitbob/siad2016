package fr.univ.tours.siad.jaxws.ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author François Robert
 */
@Path("ping")
public class PingResource {

  @GET
  public Response ping() {
    return Response.noContent().build();
  }

}
