package searchmetrics.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import searchmetrics.domain.BtcUsdRate;
import searchmetrics.domain.RateService;

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

    /**
     * RateResource.getLatest() queries the rate service to return the
     * latest rate BTC-USD in json format.
     *
     * @return the latest rate in json format
     */
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

    /**
     * RateResource.index() queries the rates database for a period of time
     * and returns the time series of the exchange rates BTC-USD for that period
     *
     * @param startDate the start date of period of time in the format yyy-MM-dd
     * @param endDate   the end date of period of time in the format yyy-MM-dd
     * @return the json array with the rates information for the specified period
     */
    @GET
    @Produces(APPLICATION_JSON)
    public Response index(
        @QueryParam("startDate") String startDate,
        @QueryParam("endDate") String endDate) {

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

    // Private Methods ////////////////////////////

    /**
     * @param startDate the start date of period of time in the format yyy-MM-dd
     * @param endDate   the end date of period of time in the format yyy-MM-dd
     * @return a List with the BtcUsdRate information for the specified period
     */
    private List<BtcUsdRate> getRates(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isBlank() || endDate.isBlank()) {
            return rateService.getRatesByDefaultPeriod();
        }

        return rateService.getRatesByPeriod(
            parse(startDate), parse(endDate));
    }

    /**
     * Parses the date queryParam from the request and throws a
     * DateTimeParseException in case of an error.
     *
     * @param startDate the string representing the date in the format yyyy-MM-dd
     * @return a LocalDate object converted from the string or null
     * for null values of the parameter
     */
    private LocalDate parse(String startDate) {
        if (startDate == null)
            return null;

        return LocalDate.parse(startDate);
    }

    /**
     * Creates a RateError for the case of an internal server error
     *
     * @param code the specific error code
     * @return the RateError object
     */
    private RateError serverError(String code) {
        return new RateError(
            code,
            "500",
            "A unexpected exception occurred. Please contact the administrator");
    }

    /**
     * Creates a RateError for the case of a bad request error
     *
     * @param code the specific error code
     * @return the RateError object
     */
    private RateError badRequestError(String code) {
        return new RateError(
            code,
            "400",
            "parameters startDate and endDate must be expressed in the format yyyy-MM-dd");
    }
}