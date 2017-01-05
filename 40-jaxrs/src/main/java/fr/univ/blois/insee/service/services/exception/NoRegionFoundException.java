package fr.univ.blois.insee.service.services.exception;

/**
 * @author François Robert
 */
public class NoRegionFoundException extends Exception {

  public NoRegionFoundException(String regionInseeId) {
    super(String.format("La region %s n'a pu être localisé", regionInseeId));
  }

}
