package searchmetrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@Path("/rates/btc-usd")
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
    @Path("/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatest() {
        try {
            return Response.ok(rateService.getLatestRate())
                .type(APPLICATION_JSON_TYPE)
                .build();
        } catch (RuntimeException re) {
            return Response.serverError()
                .entity(new RateError(
                "XC001",
                "500",
                "A unexpected exception occurred. Please contact the administrator")
            ).build();
        }
    }
}