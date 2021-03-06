package fr.univ.blois.insee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by francois on 24/09/15.
 */
@Entity
@SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = Person.FIND_ALL, query = "select p from Person p")
    , @NamedQuery(name = Person.FIND_BY_REFERENCE, query = "select p from Person p where p.reference = :" + Person.REFERENCE)
    , @NamedQuery(name = Person.FIND_BY_CITY, query = "select p from Person p where p.address.city.inseeId = :" + City.INSEEID)
    , @NamedQuery(name = Person.COUNT_FOR_REGION, query = "select count(p) from Person p where p.address.city.district.region.inseeId = :" + Person.TARGET_INSEE)
    , @NamedQuery(name = Person.COUNT_FOR_DISTRICT, query = "select count(p) from Person p where p.address.city.district.inseeId = :" + Person.TARGET_INSEE)
    , @NamedQuery(name = Person.COUNT_FOR_CITY, query = "select count(p) from Person p where p.address.city.inseeId = :" + Person.TARGET_INSEE)
    , @NamedQuery(name = Person.COUNT_NO_ADDRESS, query = "select count(p) from Person p where p.address = null")
    , @NamedQuery(name = Person.COUNT, query = "select count(p) from Person p")
})
public class Person implements Serializable {

  private static final long serialVersionUID = -3545547914183480156L;

  /**
   * Renvoi la liste des personnes
   */
  public static final String FIND_ALL = "Person.FIND_ALL";
  /**
   * Renvoi une personne selon sa reference
   */
  public static final String FIND_BY_REFERENCE = "Person.FIND_BY_REFERENCE";
  /**
   * Renvoi la liste des personnes d'une ville
   */
  public static final String FIND_BY_CITY = "Person.FIND_BY_CITY";

  public static final String REFERENCE = "reference";
  private static final String ADDRESS_FK = "ADDRESS_FK";

  public static final String COUNT_FOR_REGION = "Person.COUNT_FOR_REGION";
  public static final String COUNT_FOR_DISTRICT = "Person.COUNT_FOR_DISTRICT";
  public static final String COUNT_FOR_CITY = "Person.COUNT_FOR_CITY";
  public static final String TARGET_INSEE = "targetInsee";
  public static final String COUNT_NO_ADDRESS = "Person.COUNT_NO_ADDRESS";
  public static final String COUNT = "Person.COUNT";

  /**
   * Identifiant
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
  private Long id;

  /**
   * Prénom
   */
  @Column(length = 100)
  private String firstname;

  /**
   * Nom de famille
   */
  @Column(length = 100)
  private String surname;

  /**
   * Date de naissance
   */
  @Temporal(TemporalType.DATE)
  private Date birth;

  /**
   * N° unique
   */
  @Column(length = 7)
  private String reference;

  /**
   * Adresse
   */
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = ADDRESS_FK, referencedColumnName = Address.ADDRESS_ID)
  private Address address;

  /**
   * Constructeur
   */
  public Person() {
  }

  public Person(String firstname, String surname, Date birth) {
    this();
    setFirstname(firstname);
    setSurname(surname);
    setBirth(birth);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Person)) return false;
    Person person = (Person) o;
    return Objects.equals(getFirstname(), person.getFirstname()) &&
        Objects.equals(getSurname(), person.getSurname()) &&
        Objects.equals(getBirth(), person.getBirth());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstname(), getSurname());
  }

  @Override
  public String toString() {
    return "Person{" +
        "firstname='" + firstname + '\'' +
        ", surname='" + surname + '\'' +
        ", id=" + id +
        ", reference='" + reference + '\'' +
        '}';
  }
}
