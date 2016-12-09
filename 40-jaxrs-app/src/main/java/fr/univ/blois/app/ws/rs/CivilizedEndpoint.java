package fr.univ.blois.app.ws.rs;

import fr.univ.tours.siad.util.ws.rs.Ping;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author François Robert
 */
@Path("hello")
public class CivilizedEndpoint implements Ping {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello(@QueryParam("name") @DefaultValue("stranger") String name) {
    return "Hello  " + name;
   }

}
