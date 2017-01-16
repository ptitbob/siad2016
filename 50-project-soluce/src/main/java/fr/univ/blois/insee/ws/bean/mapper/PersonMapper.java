package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.Person;
import fr.univ.blois.insee.ws.bean.PersonDto;

/**
 * @author François Robert
 */
public interface PersonMapper {

  default PersonDto getPersonDto(Person person){
    PersonDto personDto = new PersonDto();
    personDto.setId(person.getId());
    personDto.setName(person.getFirstname() + " " + person.getSurname());
    return personDto;
  };
}
