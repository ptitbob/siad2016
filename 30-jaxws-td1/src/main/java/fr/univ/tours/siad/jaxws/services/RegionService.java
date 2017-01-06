package fr.univ.tours.siad.jaxws.services;

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

  /**
   * Création d'une région
   * @param name nom
   * @param upperName nom normalisé
   * @return region créé
   */
  public Region createRegion(String name, String upperName) {
    Region region = new Region();
    region.setName(name);
    region.setUpperName(upperName);
    entityManager.persist(region);
    region.setInseeId(generateAlphaInseeId(region.getId()));
    entityManager.merge(region);
    return region;
  }

  private String generateAlphaInseeId(Long id) {
    return Integer.toHexString(id.intValue() + 121).toUpperCase();
  }

  /**
   * Mise à jour de la région
   * @param inseeId n° INSEE de la region
   * @param name nom de la région
   * @param upperName nom en majuscule de la region
   * @param chefLieuId Id du chef lieu de la région
   * @throws NoRegionFoundException
   */
  public void updateRegion(String inseeId, String name, String upperName, String chefLieuId) throws NoRegionFoundException {
    Region region = getRegionByInseeId(inseeId);
    region.setName(name);
    region.setUpperName(upperName);
    //region.setChefLieuId(chefLieuId);
    entityManager.merge(region);
  }

  /**
   * Suppression de la région
   * @param inseeId n° insee de la région
   * @return region supprimé
   * @throws NoRegionFoundException Si la region n'a pas été trouvé
   */
  public Region removeRegionById(String inseeId) throws NoRegionFoundException {
    Region region = getRegionByInseeId(inseeId); // cette méthode DOIT renvoyer une exception. Il n'est pas utile de la catcher ici - cela peut être important qu'une deletion leve une exception
    entityManager.remove(region);
    return region;
  }

}
