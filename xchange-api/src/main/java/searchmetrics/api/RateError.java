package searchmetrics.api;

import java.util.Objects;

/**
 * RateError is a value object reporting
 * the data of an error occurred in
 * the application.
 */
public class RateError {
    private final String type;
    private final String code;
    private final String message;

    /**
     * @param type the type of error that occurred
     * @param code the error code reported to the user
     * @param message the details of the error that occurred
     */
    public RateError(String type, String code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateError rateError = (RateError) o;
        return Objects.equals(type, rateError.type) && Objects.equals(code, rateError.code) && Objects.equals(message, rateError.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, code, message);
    }
}
