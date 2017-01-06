package fr.univ.tours.siad.jaxws.ws;

/**
 * @author François Robert
 */

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SiadHelloService", portName = "SiadHello", name = "SiadHello")
public class SayHello {

  public SayHello() {
  }

  @WebMethod
  public String SayHello(@WebParam String name) {
    return "Bonjour " + name;
  }

}
