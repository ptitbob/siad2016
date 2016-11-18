package fr.univ.tours.siad.util.data;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by francois on 11/06/15.
 */
public class EntityManagerProducer {

    @Produces @SiadDatabase
    @PersistenceContext(unitName = "siad")
    private EntityManager entityManager;

}
