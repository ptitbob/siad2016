package fr.univ.tours.siad.jaxws.services;

import fr.univ.tours.siad.util.data.bean.Region;

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
public class RegionService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  /**
   * Renvoi la liste des région
   * @return liste des région
   */
  public List<Region> getRegionList() {
    return entityManager
        .createNamedQuery(Region.FIND_ALL, Region.class)
        .getResultList();
  }

  /**
   * Renvoi une région en fonction de son n° INSEE
   * @param inseeId n° insee de la région
   * @return région
   */
  public Region getRegionById(String inseeId) {
    try {
      return entityManager
          .createNamedQuery(Region.FIND_BY_INSEEID, Region.class)
          .setParameter(Region.INSEEID, inseeId)
          .getSingleResult();
    } catch (NonUniqueResultException | NoResultException e) {
      // oups...
      return null;
    }
  }

}
