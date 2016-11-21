package fr.univ.blois.insee.service.services.exception;

import javax.ws.rs.NotFoundException;

/**
 * @author François Robert
 */
public class NoRegionFoundException extends NotFoundException {

  public NoRegionFoundException(String regionInseeId) {
    super(String.format("La region %s n'a pu être localisé", regionInseeId));
  }

}
