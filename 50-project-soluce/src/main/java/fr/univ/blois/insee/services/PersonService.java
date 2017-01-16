package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.Address;
import fr.univ.blois.insee.model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author François Robert
 */
@Stateless
public class PersonService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  public List<Person> getPersonList() {
    Address address = new Address();
    address.setLine1("ljkfhdslkjhfdslkjh");
    entityManager.persist(address);
    Person person = new Person();
    person.setFirstname("toto");
    person.setSurname("tata");
    person.setAddress(address);
    entityManager.persist(person);

    return entityManager.createNamedQuery(Person.FIND_ALL, Person.class)
        .getResultList();
  }
}
