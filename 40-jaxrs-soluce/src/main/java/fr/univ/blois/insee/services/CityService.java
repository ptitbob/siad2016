package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.services.exception.NoCityFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 * @author François Robert
 */
@Stateless
public class CityService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  public City getCityByInsee(String inseeId) throws NoCityFoundException {
    try {
      return entityManager.createNamedQuery(City.FIND_BY_INSEE, City.class)
          .setParameter(City.INSEEID, inseeId)
          .getSingleResult();
    } catch (NoResultException | NonUniqueResultException e) {
      throw new NoCityFoundException("La ville (Insee " + inseeId + ") n'a pas été localisé");
    }
  }

}
