package fr.univ.blois.insee.ws.rs;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author François Robert
 */
@Path("bonjour")
public class FriendlyResource {

  /**
   * Valeur par défaut attendu dans un requete
   */
  private static final String DEFAULT_QUERY_PARAM_VALUE = "&ToTo";

  /**
   * Methode renvoyant bonjour le monde si aucun paramètre n'est passé, sinon dit bonjour au nom passé en paramètre
   * @param who nom à qui dir bonjour
   * @return phrase disant bonjour le monde ou disant bonjour à qui ;)
   */
  @GET
  @Path("lemonde")
  public String sayHelloToTheWorld(@QueryParam("qui") @DefaultValue(DEFAULT_QUERY_PARAM_VALUE) String who) {
    return "Bonjour " + (DEFAULT_QUERY_PARAM_VALUE.equals(who) ? "le monde" : who);
  }

}
