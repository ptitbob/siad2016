package fr.univ.blois.insee.service.services;

import fr.univ.blois.insee.service.services.exception.NoRegionFoundException;
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

  public List<Region> getRegionList() {
    List<Region> regionList = entityManager.createNamedQuery(Region.FIND_ALL, Region.class).getResultList();
    return regionList;
  }

  /**
   * Renvoi la region en fonction de son numero INSEE
   * @param regionInseeId numero INSEE
   * @return region
   * @throws NoRegionFoundException si la region n'a pas été trouvé
   */
  public Region getRegionByInseeId(String regionInseeId) throws NoRegionFoundException {
    Region region;
    try {
      region = entityManager.createNamedQuery(Region.FIND_BY_INSEEID, Region.class).setParameter(Region.INSEEID, regionInseeId).getSingleResult();
    } catch (NoResultException | NonUniqueResultException e) {
      throw new NoRegionFoundException(regionInseeId);
    }
    return region;
  }


}
