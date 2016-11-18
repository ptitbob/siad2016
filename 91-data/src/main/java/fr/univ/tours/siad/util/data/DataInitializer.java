package fr.univ.tours.siad.util.data;

import fr.univ.tours.siad.util.log.Traceable;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by francois on 31/08/15.
 */

@Startup
@Singleton
public class DataInitializer {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    @Inject
    private StatisticServices statisticServices;

    @Inject
    private Logger logger;

    @PostConstruct @Traceable
    public void initializeData() {
        logger.debug("----------------- Data initialisation -----------------");
        logger.debug("Regions : " + statisticServices.getCount() + " presente en base.");
    }

}
