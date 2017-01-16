package fr.univ.blois.insee.ws.bean;

import java.io.Serializable;

/**
 * @author François Robert
 */
public class PersonDto implements Serializable {
  private Long id;
  private String name;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
