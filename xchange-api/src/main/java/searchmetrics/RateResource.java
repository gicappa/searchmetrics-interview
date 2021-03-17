package searchmetrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rates")
public class RateResource {

    private final RateService rateService;

    /**
     * RateResource class has the rateService as a collaborator
     * object that will call the business logic of the application
     *
     * @param rateService the service of the business logic
     */
    public RateResource(RateService rateService) {
        this.rateService = rateService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatest() {
        rateService.getLatestRate();
        return Response.status(Response.Status.OK).build();
    }
}