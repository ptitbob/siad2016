package fr.univ.blois.insee.services.exception;

/**
 * @author François Robert
 */
public class DistrictNotFoundExcetion extends Exception {
  public DistrictNotFoundExcetion(String districtInsee) {
    super(String.format("Le département %s n'a pas été trouvé", districtInsee));
  }
}
