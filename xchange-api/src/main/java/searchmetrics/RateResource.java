package searchmetrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
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
    @Produces(APPLICATION_JSON)
    public Response getLatest() {
        try {
            return Response.ok(rateService.getLatestRate())
                .type(APPLICATION_JSON_TYPE)
                .build();
        } catch (RuntimeException re) {
            return Response.serverError()
                .entity(serverError("XC001"))
                .type(APPLICATION_JSON_TYPE)
                .build();
        }
    }

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    public Response index(String startDate, String endDate) {
        try {

            return Response.ok(getRates(startDate, endDate))
                .type(APPLICATION_JSON_TYPE).build();

        } catch (DateTimeParseException re) {

            return Response.status(Response.Status.BAD_REQUEST)
                .entity(badRequestError("XC003"))
                .build();

        } catch (RuntimeException re) {

            return Response.serverError().entity(serverError("XC002"))
                .type(APPLICATION_JSON_TYPE)
                .build();
        }
    }

    private List<BtcUsdRate> getRates(String startDate, String endDate) {
        if (startDate == null && endDate == null) {
            return rateService.getRatesDefault();
        }

        return rateService.getRatesBetween(
            parse(startDate), parse(endDate));
    }

    private LocalDate parse(String startDate) {
        if (startDate == null)
            return null;

        return LocalDate.parse(startDate);
    }

    private RateError serverError(String code) {
        return new RateError(
            code,
            "500",
            "A unexpected exception occurred. Please contact the administrator");
    }

    private RateError badRequestError(String code) {
        return new RateError(
            code,
            "400",
            "startDate or endDate parameters must be expressed in the format yyyy-MM-dd");
    }
}