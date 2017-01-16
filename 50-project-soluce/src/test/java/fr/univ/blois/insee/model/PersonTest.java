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
    /*
    Address address = new Address("line", "zipcode", "city");
    persist(address);
    Assert.assertEquals(new Long(1), person.getId());
    person = new Person("login", "surname", "firstname");
    person = persist(person);
    */
  }

}
