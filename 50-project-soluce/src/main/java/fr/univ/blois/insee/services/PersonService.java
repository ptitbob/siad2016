package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.Address;
import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.model.Person;
import fr.univ.blois.insee.model.ZipCode;
import fr.univ.blois.insee.services.exception.CityNotFoundException;
import fr.univ.blois.insee.services.exception.PersonNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author François Robert
 */
@Stateless
public class PersonService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  public List<Person> getList() {
    return entityManager.createNamedQuery(Person.FIND_ALL, Person.class)
        .getResultList();
  }

  public Person getForReference(String personReference) throws PersonNotFoundException {
    try {
      return entityManager.createNamedQuery(Person.FIND_BY_REFERENCE, Person.class)
          .setParameter(Person.REFERENCE, personReference)
          .getSingleResult();
    } catch (NonUniqueResultException | NoResultException e) {
      throw new PersonNotFoundException(personReference);
    }
  }

  public Person create(Person person) {
    entityManager.persist(person);
    person.setReference(Long.toString(person.getId(), 36).toUpperCase());
    entityManager.merge(person);
    return person;
  }

  public void delete(String personReference) throws PersonNotFoundException {
    Person person = getForReference(personReference);
    entityManager.remove(person);
  }

  public void update(Person person) {
    entityManager.merge(person);
  }

  public List<City> getCityListForZipCode(String zipCode) throws CityNotFoundException {
    List<City> cityList = entityManager.createNamedQuery(City.GET_BY_ZIPCODE, City.class)
        .setParameter(ZipCode.ZIPCODE, zipCode)
        .getResultList();
    if (cityList.isEmpty()) {
      throw new CityNotFoundException("Aucune ville trouvée avec le code postal " + zipCode);
    } else {
      return cityList;
    }
  }


  public Person persistAndSetAddress(Person person, String floor, String line1, String line2, ZipCode zipCode, City city) {
    Address address = new Address();
    setAddressValues(floor, line1, line2, zipCode, city, address);
    entityManager.persist(address);
    person.setAddress(address);
    entityManager.merge(person);
    return person;
  }

  public Person mergeAddress(Person person, String floor, String line1, String line2, ZipCode zipCode, City city) {
    Address address = person.getAddress();
    setAddressValues(floor, line1, line2, zipCode, city, address);
    entityManager.merge(address);
    return person;
  }

  private void setAddressValues(String floor, String line1, String line2, ZipCode zipCode, City city, Address address) {
    try {
      address.setFloor(Integer.valueOf(floor));
    } catch (NumberFormatException e) {
      address.setFloor(null);
    }
    address.setLine1(line1);
    address.setLine2(line2);
    address.setZipCode(zipCode);
    address.setCity(city);
  }

  public void removeAddressFromPerson(String personReference) throws PersonNotFoundException {
    Person person = getForReference(personReference);
    Address address = person.getAddress();
    person.setAddress(null);
    entityManager.persist(person);
    if (address != null) {
      entityManager.remove(address);
    }
  }

  public Long getCountForRegion(String inseeId) {
    return getCountWith(Person.COUNT_FOR_REGION, inseeId);
  }

  public Long getCountForDistrict(String inseeId) {
    return getCountWith(Person.COUNT_FOR_DISTRICT, inseeId);
  }

  public Long getCountForCity(String inseeId) {
    return getCountWith(Person.COUNT_FOR_CITY, inseeId);
  }

  public Long getCountPersonWithoutAddress() {
    return getCountWith(Person.COUNT_NO_ADDRESS);
  }

  public Long getCount() {
    return getCountWith(Person.COUNT);
  }

  private Long getCountWith(String queryName) {
    try {
      return entityManager.createNamedQuery(queryName, Long.class)
          .getSingleResult();
    } catch (NonUniqueResultException | NoResultException e) {
      return 0L;
    }
  }

  private Long getCountWith(String queryName, String inseeId) {
    try {
      return entityManager.createNamedQuery(queryName, Long.class)
          .setParameter(Person.TARGET_INSEE, inseeId)
          .getSingleResult();
    } catch (NonUniqueResultException | NoResultException e) {
      return 0L;
    }
  }

}
