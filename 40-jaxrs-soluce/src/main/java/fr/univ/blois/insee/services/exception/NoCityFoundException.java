package fr.univ.blois.insee.services.exception;

/**
 * @author François Robert
 */
public class NoCityFoundException extends Exception {
  public NoCityFoundException(String Message) {
    super(Message);
  }
}
