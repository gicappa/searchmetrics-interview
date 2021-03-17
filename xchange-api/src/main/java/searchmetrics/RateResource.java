package searchmetrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rates")
public class RateResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatest() {
        return Response.status(Response.Status.OK).build();
    }
}