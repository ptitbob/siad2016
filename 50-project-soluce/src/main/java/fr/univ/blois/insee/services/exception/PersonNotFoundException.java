package fr.univ.blois.insee.services.exception;

/**
 * @author François Robert
 */
public class PersonNotFoundException extends Exception {
  private final String personReference;

  public PersonNotFoundException(String personReference) {
    this.personReference = personReference;
  }

  public String getPersonReference() {
    return personReference;
  }

}
