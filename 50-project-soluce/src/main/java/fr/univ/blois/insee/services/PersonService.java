package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.Person;
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
}
