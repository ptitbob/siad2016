package fr.univ.blois.insee.ws.rs.exception;

import fr.univ.blois.insee.model.Person;

/**
 * @author François Robert
 */
public class PersonWithoutAddressException extends Exception {
  public PersonWithoutAddressException(Person person) {
    super(String.format("%s %s (%s) n'a pas d'adresse assocliée", person.getFirstname(), person.getSurname(), person.getReference()));
  }
}
