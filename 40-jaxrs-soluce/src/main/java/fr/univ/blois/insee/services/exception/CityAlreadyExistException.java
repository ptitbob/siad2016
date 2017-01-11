package fr.univ.blois.insee.services.exception;

/**
 * La ville existe déjà
 * @author François Robert
 */
public class CityAlreadyExistException extends Exception {

  public CityAlreadyExistException() {
    super("La ville existe déjà");
  }
}
