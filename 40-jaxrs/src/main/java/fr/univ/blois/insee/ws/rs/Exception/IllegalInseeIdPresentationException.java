package fr.univ.blois.insee.ws.rs.Exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * @author François Robert
 */
public class IllegalInseeIdPresentationException extends ClientErrorException {

  public IllegalInseeIdPresentationException(String message) {
    super(message, Response.Status.PRECONDITION_FAILED);
  }

}
