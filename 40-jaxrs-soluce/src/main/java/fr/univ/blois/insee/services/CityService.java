package fr.univ.blois.insee.services;

import fr.univ.blois.insee.model.City;
import fr.univ.blois.insee.model.District;
import fr.univ.blois.insee.model.Region;
import fr.univ.blois.insee.model.ZipCode;
import fr.univ.blois.insee.services.exception.CityAlreadyExistException;
import fr.univ.blois.insee.services.exception.NoCityFoundException;
import fr.univ.blois.insee.ws.bean.CityDto;

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
public class CityService {

  @PersistenceContext(unitName = "siad")
  private EntityManager entityManager;

  /**
   * Renvoi la ville identifié par le n° INSEE
   * @param inseeId n° INSEE de la ville
   * @return Ville
   * @throws NoCityFoundException Erreur, la ville n'a pas été trouvé
   */
  public City getCityByInsee(String inseeId) throws NoCityFoundException {
    try {
      return entityManager.createNamedQuery(City.FIND_BY_INSEE, City.class)
          .setParameter(City.INSEEID, inseeId)
          .getSingleResult();
    } catch (NoResultException | NonUniqueResultException e) {
      throw new NoCityFoundException("La ville (Insee " + inseeId + ") n'a pas été localisé");
    }
  }

  /**
   * Renvoi la liste de toutes les villes enregistré
   * @return liste
   */
  public List<City> getCityList() {
    return entityManager.createNamedQuery(City.FIND_ALL, City.class)
        .getResultList();
  }

  /**
   * Renvoi la liste des villes d'une region
   * @param regionInsee n° INSEE de la région
   * @return liste
   */
  public List<City> getRegionCityList(String regionInsee) {
    return entityManager.createNamedQuery(City.FIND_BY_REGION, City.class)
        .setParameter(Region.INSEEID, regionInsee)
        .getResultList()
        ;
  }

  /**
   * Renvoi la liste de toutes les villes d'un département
   * @param districtInsee n° INSEE du département
   * @return liste
   */
  public List<City> getDistrictCityList(String districtInsee) {
    return entityManager.createNamedQuery(City.FIND_BY_DISTRICT, City.class)
        .setParameter(District.INSEEID, districtInsee)
        .getResultList()
        ;
  }

  /**
   * Création d'une ville
   * @param region n° INSEE de la region
   * @param district n° INSEE du département
   * @param cityName Nom de la ville
   * @param cityInsee n° INSEE de la ville
   * @return ville crée
   * @throws CityAlreadyExistException Erreur, la ville existe déjà
   */
  public City createCity(Region region, District district, String cityName, String cityInsee) throws CityAlreadyExistException {
    try {
      // On test si la ville existe
      getCityByInsee(cityInsee);
      // Si oui, erreur
      throw new CityAlreadyExistException();
    } catch (NoCityFoundException e) {
      // Si non, on la crée !
      // C'est tordu mais fun
      City city = new City();
      city.setRegion(region);
      city.setDistrict(district);
      city.setName(cityName);
      city.setInseeId(cityInsee);
      entityManager.persist(city);
      return city;
    }
  }

  /**
   * Supprime une ville
   * @param cityinsee n° INSEE de la ville a supprimer
   */
  public void remove(String cityinsee) {
    try {
      City city = getCityByInsee(cityinsee);
      entityManager.remove(city);
    } catch (NoCityFoundException e) {
      // Logger etc...
    }
  }

  /**
   * Ajout d'un code postal à une ville
   * @param city ville
   * @param zipCodeValue code postal
   * @return Code postal créé
   */
  public ZipCode addZipCode(City city, String zipCodeValue) {
    ZipCode zipCode = new ZipCode(city, zipCodeValue);
    entityManager.persist(zipCode);
    return zipCode;
  }
}
