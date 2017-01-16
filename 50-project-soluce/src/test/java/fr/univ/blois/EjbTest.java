package fr.univ.blois;

import org.junit.BeforeClass;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author François Robert
 */
public class EjbTest {

  protected static EJBContainer ejbContainer;
  protected static Context context;

  @BeforeClass
  public static void initializeContainer() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(EJBContainer.MODULES, new File("target/test-classes"));
    ejbContainer = EJBContainer.createEJBContainer(properties);
    context = ejbContainer.getContext();
  }

  public <E> E lookup(Class<E> beanClass) throws NamingException {
    return  (E) context.lookup("java:global/test-classes/" + beanClass.getSimpleName());
  }
}
