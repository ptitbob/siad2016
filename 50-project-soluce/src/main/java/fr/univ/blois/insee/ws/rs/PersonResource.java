package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.services.PersonService;
import fr.univ.blois.insee.ws.bean.PersonDto;
import fr.univ.blois.insee.ws.bean.mapper.PersonMapper;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author François Robert
 */
@Path("personnes")
public class PersonResource implements PersonMapper {

  @EJB
  private PersonService personService;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<PersonDto> getPersonList() {
    return personService.getPersonList()
        .stream().map(this :: getPersonDto)
        .collect(Collectors.toList());
  }

}
