package fr.univ.blois.insee.ws.rs.Exception;

/**
 * Le département ne se trouve pas dans la région
 * @author François Robert
 */
public class UnconsistantRegionDistrictRelation extends IllegalStateException {
  public UnconsistantRegionDistrictRelation() {
    super("Le departement n'appartient pas à la region");
  }
}
