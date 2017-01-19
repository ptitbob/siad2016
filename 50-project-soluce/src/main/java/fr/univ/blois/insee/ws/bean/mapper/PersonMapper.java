package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.Person;
import fr.univ.blois.insee.ws.bean.PersonDto;

/**
 * @author François Robert
 */
public interface PersonMapper extends AddressMapper {

  default PersonDto getPersonDto(Person person){
    PersonDto personDto =  new PersonDto(person.getReference(), person.getFirstname() + " " + person.getSurname());
    if (person.getAddress() != null) {
      personDto.setAddress(getAddressDto(person.getAddress()));
    }
    return personDto;
  }

}
