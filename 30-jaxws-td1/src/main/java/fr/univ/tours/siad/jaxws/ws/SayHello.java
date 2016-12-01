package fr.univ.tours.siad.jaxws.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author François Robert
 */
@WebService(serviceName = "SiadHelloService", portName = "SiadHello", name = "SiadHello")
public class SayHello {

  @WebMethod
  public String sayHello() {
    return "Hello Siad";
  }

}
