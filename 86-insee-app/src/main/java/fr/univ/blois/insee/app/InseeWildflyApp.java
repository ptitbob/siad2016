package fr.univ.blois.insee.app;

import fr.univ.blois.insee.service.InseeApplication;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

/**
 * @author François Robert
 */
public class InseeWildflyApp {

  static public void main(String... args) {
    try {
      Swarm containerSwarm = new Swarm();
      String driverModule = intializeDatabaseFraction(containerSwarm);

      JAXRSArchive deploymentArchive = ShrinkWrap.create(JAXRSArchive.class);
      deploymentArchive.addModule(driverModule);
      deploymentArchive.addPackages(true, InseeApplication.class.getPackage());

      // Lancement du serveur
      containerSwarm.start();
      // Ajout de "l'archive"
      containerSwarm.deploy(deploymentArchive);

    } catch (Exception e) {
      // todo something
    }
  }

  /**
   * Initialise les datasources attaché au projet
   *
   * @param containerSwarm container wildfly swarm
   * @return nom du module de db
   */
  private static String intializeDatabaseFraction(Swarm containerSwarm) {
    System.setProperty("swarm.use.db", "postgresql");
    containerSwarm.fraction(new DatasourcesFraction()
        .jdbcDriver("org.postgresql", (jdbcDriver) -> {
          jdbcDriver.driverClassName("org.postgresql.Driver");
          jdbcDriver.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
          jdbcDriver.driverModuleName("org.postgresql");
        })
        .dataSource("SiadDS", (dataSource) -> {
          dataSource.driverName("org.postgresql");
          dataSource.connectionUrl("jdbc:postgresql://localhost:5432/siad2016db");
          dataSource.userName("siad");
          dataSource.password("siad2015");
        })
    );
    containerSwarm.fraction(
        new JPAFraction().defaultDatasource("java:jboss/datasources/SiadDS")
    );
    return "org.postgresql";
  }

}
