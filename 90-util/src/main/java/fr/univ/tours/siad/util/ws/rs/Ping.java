package fr.univ.tours.siad.util.ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by francois on 31/08/15.
 */
public interface Ping {

    @GET
    @Path("ping")
    default Response Ping() {
        return Response.noContent().build();
    }

}
