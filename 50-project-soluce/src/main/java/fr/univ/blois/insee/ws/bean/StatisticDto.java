package fr.univ.blois.insee.ws.bean;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author François Robert
 */
@XmlRootElement
@XmlAccessorType(FIELD)
public class StatisticDto {

  @XmlAttribute
  private String target;
  private URI targetUrl;
  private Long personCountIn;
  private Long unassignedPerson;

  public void setTarget(String target) {
    this.target = target;
  }

  public String getTarget() {
    return target;
  }

  public void setTargetUrl(URI targetUrl) {
    this.targetUrl = targetUrl;
  }

  public URI getTargetUrl() {
    return targetUrl;
  }

  public void setPersonCountIn(Long personCountIn) {
    this.personCountIn = personCountIn;
  }

  public Long getPersonCountIn() {
    return personCountIn;
  }

  public void setUnassignedPerson(Long unassignedPerson) {
    this.unassignedPerson = unassignedPerson;
  }

  public Long getUnassignedPerson() {
    return unassignedPerson;
  }
}
