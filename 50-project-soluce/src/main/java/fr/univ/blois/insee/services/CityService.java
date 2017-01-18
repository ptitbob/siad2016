package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.model.District;
import fr.univ.blois.insee.ws.bean.DistrictCityListDto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author François Robert
 */
@Stateless
public class CityService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  public List<City> getCityListForDistrict(String districtInseeId) {
    return entityManager.createNamedQuery(City.FIND_BY_DISTRICT, City.class)
        .setParameter(District.INSEEID, districtInseeId)
        .getResultList();
  }
}
