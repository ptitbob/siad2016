package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.District;
import fr.univ.blois.insee.model.Region;
import fr.univ.blois.insee.services.exception.NoDsitrictFoundException;
import fr.univ.blois.insee.ws.bean.DistrictDto;

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
public class DistrictService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  /**
   * Renvoi la liste des département d'une région en fonction du numéro Insee de la région
   * @param regionInseeId n° INSEE de la région
   * @return liste
   */
  public List<District> getDistrictListForRegion(String regionInseeId) {
    return entityManager.createNamedQuery(District.FIND_BY_REGION, District.class)
        .setParameter(Region.INSEEID, regionInseeId)
        .getResultList();
  }

  /**
   * Renvoi la liste de tous les département
   * @return liste
   */
  public List<District> getDistrictList() {
    return entityManager.createNamedQuery(District.FIND_ALL, District.class)
        .getResultList();
  }

  /**
   * Renvoi le département via son numéro INSEE
   * @param inseeId n° INSEE
   * @return département
   * @throws NoDsitrictFoundException Erreur si le département n'a pu être localisé
   */
  public District getDistrictbyInsee(String inseeId) throws NoDsitrictFoundException {
    try {
      return entityManager.createNamedQuery(District.FIND_BY_INSEEID, District.class)
          .setParameter(District.INSEEID, inseeId)
          .getSingleResult();
    } catch (NoResultException | NonUniqueResultException e) {
      throw new NoDsitrictFoundException("le département " + inseeId + " n'a pu être localisé");
    }
  }
}
