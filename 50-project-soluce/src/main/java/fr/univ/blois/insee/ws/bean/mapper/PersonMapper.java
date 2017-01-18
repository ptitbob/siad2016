package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.Person;
import fr.univ.blois.insee.ws.bean.PersonDto;

/**
 * @author François Robert
 */
public interface PersonMapper {

  default PersonDto getPersonDto(Person person){
    return new PersonDto(person.getReference(), person.getFirstname() + " " + person.getSurname());
  };
}
