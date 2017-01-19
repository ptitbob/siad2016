package fr.univ.blois.insee.ws.rs;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.model.Person;
import fr.univ.blois.insee.model.ZipCode;
import fr.univ.blois.insee.services.PersonService;
import fr.univ.blois.insee.services.exception.CityNotFoundException;
import fr.univ.blois.insee.services.exception.PersonNotFoundException;
import fr.univ.blois.insee.ws.bean.AddressDto;
import fr.univ.blois.insee.ws.bean.PersonDto;
import fr.univ.blois.insee.ws.bean.mapper.PersonMapper;
import fr.univ.blois.insee.ws.rs.Exception.CityZipcodeNotCorrespondingException;
import fr.univ.blois.insee.ws.rs.Exception.PersonWithoutAddressException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author François Robert
 */
@Path("personnes")
public class PersonResource implements PersonMapper {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-YYYY");

  @EJB
  private PersonService personService;

  @GET
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public List<PersonDto> getPersonList() {
    return personService.getList()
        .stream().map(this :: getPersonDto)
        .collect(Collectors.toList());
  }

  @GET
  @Path("/{ref:[A-Z,0-9]*}")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public PersonDto getBersonForReference(@PathParam("ref") String personReference) throws PersonNotFoundException {
    return getPersonDto(personService.getForReference(personReference));
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces({WILDCARD, APPLICATION_JSON, APPLICATION_XML})
  public Response createPerson(
      @Context HttpHeaders httpHeaders
      , @FormParam("prenom") String firstname
      , @FormParam("nom") String surname
      , @FormParam("naissance") String dateOfBirth
  ) {
    Date birth = getDateOfBirthFrom(dateOfBirth);
    Person person = personService.create(new Person(firstname, surname, birth));
    URI personURI = UriBuilder
        .fromResource(PersonResource.class)
        .path(person.getReference())
        .build();
    return Response.created(personURI)
        .entity(
            httpHeaders.getAcceptableMediaTypes().contains(WILDCARD_TYPE) ? null : getPersonDto(person)
        )
        .build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces({WILDCARD, APPLICATION_JSON, APPLICATION_XML})
  @Path("/{ref:[A-Z,0-9]*}")
  public Response updatePerson(
      @Context HttpHeaders httpHeaders
      , @PathParam("ref") String personReference
      , @FormParam("reference") String reference
      , @FormParam("prenom") String firstname
      , @FormParam("nom") String surname
      , @FormParam("naissance") String dateOfBirth
  ) throws PersonNotFoundException {
    if (personReference.equals(reference)) {
      Date birth = getDateOfBirthFrom(dateOfBirth);
      Person person = personService.getForReference(personReference);
      person.setFirstname(firstname);
      person.setSurname(surname);
      person.setBirth(birth);
      personService.update(person);
      return Response.ok()
          .entity(
              httpHeaders.getAcceptableMediaTypes().contains(WILDCARD_TYPE) ? null : getPersonDto(person)
          )
          .build();
    } else {
      throw new NotAcceptableException();
    }
  }

  private Date getDateOfBirthFrom(@FormParam("naissance") String dateOfBirth) {
    Date birth;
    try {
      birth = DATE_FORMAT.parse(dateOfBirth);
    } catch (ParseException e) {
      birth = null;
    }
    return birth;
  }

  @DELETE
  @Path("/{ref:[A-Z,0-9]*}")
  public Response deletePerson(@PathParam("ref") String personReference) {
    try {
      personService.delete(personReference);
    } catch (PersonNotFoundException e) {
      // log something
    }
    return Response.ok().build();
  }

  @GET
  @Path("/{ref:[A-Z,0-9]*}/adresse")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public AddressDto getPersonAddress(
      @PathParam("ref") String personReference
  ) throws PersonNotFoundException, PersonWithoutAddressException {
    Person person = personService.getForReference(personReference);
    if (person.getAddress() == null) {
      throw new PersonWithoutAddressException(person);
    } else {
      return getAddressDto(person.getAddress());
    }
  }


  @POST
  @Path("/{ref:[A-Z,0-9]*}/adresse")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces({WILDCARD, APPLICATION_JSON, APPLICATION_XML})
  public Response createAndSetAddressToPerson(
      @Context HttpHeaders httpHeaders
      , @PathParam("ref") String personReference
      , @FormParam("etage") String floor
      , @FormParam("ligne1") String line1
      , @FormParam("ligne2") String line2
      , @FormParam("codepostal") String zipCode
      , @FormParam("ville") String townName
  ) throws CityNotFoundException, PersonNotFoundException, CityZipcodeNotCorrespondingException {
    Person person = personService.getForReference(personReference);
    City city = getCity(zipCode, townName);
    ZipCode addressZipCode = getZipCode(zipCode, city);
    personService.persistAndSetAddress(person, floor, line1, line2, addressZipCode, city);
    return Response.ok()
        .entity(
            httpHeaders.getAcceptableMediaTypes().contains(WILDCARD_TYPE) ? null : getPersonDto(person)
        )
        .build();
  }

  @PUT
  @Path("/{ref:[A-Z,0-9]*}/adresse")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces({WILDCARD, APPLICATION_JSON, APPLICATION_XML})
  public Response updateAddress(
      @Context HttpHeaders httpHeaders
      , @PathParam("ref") String personReference
      , @FormParam("etage") String floor
      , @FormParam("ligne1") String line1
      , @FormParam("ligne2") String line2
      , @FormParam("codepostal") String zipCode
      , @FormParam("ville") String townName
  ) throws CityNotFoundException, PersonNotFoundException, CityZipcodeNotCorrespondingException, PersonWithoutAddressException {
    Person person = personService.getForReference(personReference);
    if (person.getAddress() == null) {
      throw new PersonWithoutAddressException(person);
    }
    City city = getCity(zipCode, townName);
    ZipCode addressZipCode = getZipCode(zipCode, city);
    personService.mergeAddress(person, floor, line1, line2, addressZipCode, city);
    return Response.ok()
        .entity(
            httpHeaders.getAcceptableMediaTypes().contains(WILDCARD_TYPE) ? null : getPersonDto(person)
        )
        .build();
  }

  @DELETE
  @Path("/{ref:[A-Z,0-9]*}/adresse")
  public Response removeAddressFromPerson(@PathParam("ref") String personReference) throws PersonNotFoundException {
    personService.removeAddressFromPerson(personReference);
    return Response.ok().build();
  }

  private ZipCode getZipCode(@FormParam("codepostal") String zipCode, City city) {
    return city.getZipCodeSet()
          .stream()
          .filter(cityZipCode -> zipCode.equals(cityZipCode.getZipCode()))
          .findFirst().orElse(null);
  }

  private City getCity(@FormParam("codepostal") String zipCode, @FormParam("ville") String townName) throws CityNotFoundException, CityZipcodeNotCorrespondingException {
    List<City> cityList = personService.getCityListForZipCode(zipCode);
    City city = personService.getCityListForZipCode(zipCode)
        .stream()
        .filter(cityInList -> townName.toUpperCase().equals(cityInList.getName().toUpperCase()))
        .findFirst()
        .orElse(null);
    if (city == null) {
      // primaire - faire evoluer ;)
      throw new CityZipcodeNotCorrespondingException(townName, zipCode);
    }
    return city;
  }

}
