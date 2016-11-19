package fr.univ.blois.insee.service.services;

import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

}
