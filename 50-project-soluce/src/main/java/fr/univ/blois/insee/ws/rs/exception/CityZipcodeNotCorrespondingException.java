package fr.univ.blois.insee.ws.rs.exception;

/**
 * @author François Robert
 */
public class CityZipcodeNotCorrespondingException extends Exception {
  public CityZipcodeNotCorrespondingException(String townName, String zipCode) {
    super(String.format("La ville %s et le code postal %s ne corresponde pas", townName.toUpperCase(), zipCode));
  }
}
