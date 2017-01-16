package fr.univ.blois;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author François Robert
 */
public class JpaTest {

  private static EntityManagerFactory entityManagerFactory;
  protected static EntityManager entityManager;


  @BeforeClass
  public static void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("siadTestPU");
    entityManager = entityManagerFactory.createEntityManager();
  }

  @AfterClass
  public static void tearDown() {
    if (entityManager != null) {
      entityManager.close();
    }
    if (entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }

  protected EntityManager getEntityManager() {
    return entityManager;
  }

  protected <E> E find(Class<E> entityClass, Object id) {
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    E entity = entityManager.find(entityClass, id);
    entityTransaction.commit();
    return entity;
  }

  /**
   * Persiste en base une entité
   * @param entity entité
   * @param <E> classe de l'entité
   * @return entité persitée
   */
  protected <E> E persist(E entity) {
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    entityManager.persist(entity);
    entityTransaction.commit();
    return entity;
  }

  /**
   * Met à jour une entité en base
   * @param entity entité
   * @param <E> classe de l'entité
   */
  protected <E> void merge(E entity) {
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    entityManager.merge(entity);
    entityTransaction.commit();
  }

}
