package fr.univ.tours.siad.jaxws.services;

import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

}
