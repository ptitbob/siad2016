package fr.univ.tours.siad.util.data;

import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Queue;

/**
 * Created by francois on 11/09/15.
 */
@Stateless
public class StatisticServices {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    public Long getCount() {
        return entityManager.createNamedQuery(Region.COUNT, Long.class).getSingleResult();
    }
}
