package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.District;
import fr.univ.blois.insee.model.Region;
import fr.univ.blois.insee.services.exception.DistrictNotFoundExcetion;

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

  public District getDistrictByInsee(String districtInsee) throws DistrictNotFoundExcetion {
    try {
      return entityManager.createNamedQuery(District.FIND_BY_INSEEID, District.class)
          .setParameter(District.INSEEID, districtInsee)
          .getSingleResult();
    } catch (NoResultException | NonUniqueResultException e) {
      throw new DistrictNotFoundExcetion(districtInsee);
    }
  }
}
