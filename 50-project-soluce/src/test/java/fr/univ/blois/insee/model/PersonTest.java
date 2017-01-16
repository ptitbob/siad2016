package fr.univ.blois.insee.model;

import fr.univ.blois.JpaTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author François Robert
 */
public class PersonTest extends JpaTest {

  @Test
  public void personTest() {
    Person person = new Person();
    person.setFirstname("toto");
    person.setReference("AAAA");
    person = persist(person);
    Address address = new Address();
    address.setLine1("line for test");
    persist(address);
    person.setAddress(address);
    merge(person);
    entityManager.detach(person);
    Person personAfterMerging = entityManager.find(Person.class, person.getId());
    Assert.assertNotSame(person, personAfterMerging);
    Assert.assertNotNull(personAfterMerging.getAddress());
  }

}
