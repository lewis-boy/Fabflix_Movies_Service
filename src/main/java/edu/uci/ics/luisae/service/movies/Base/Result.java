package edu.uci.ics.luisae.service.movies.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response.Status;

public enum Result {

    PLEVEL_OUT_OF_RANGE  (-14, "Privilege level out of valid range.", Status.BAD_REQUEST),
    TOKEN_INVALID_LENGTH (-13, "Token has invalid length.",Status.BAD_REQUEST),
    PASSWORD_INVALID_LENGTH (-12, "Password has invalid length.",Status.BAD_REQUEST),
    EMAIL_INVALID_FORMAT (-11, "Email address has invalid format",Status.BAD_REQUEST),
    EMAIL_INVALID_LENGTH (-10, "Email address has invalid length",Status.BAD_REQUEST),
    JSON_PARSE_EXCEPTION   (-3, "JSON Parse Exception.",   Status.BAD_REQUEST),
    JSON_MAPPING_EXCEPTION (-2, "JSON Mapping Exception.", Status.BAD_REQUEST),

    INTERNAL_SERVER_ERROR  (-1, "Internal Server Error.",  Status.INTERNAL_SERVER_ERROR),

    PASSWORD_DONT_MATCH             (11, "Passwords do not match.",Status.OK),
    PASSWORD_LENGTH_UNSATISFIED (12, "Password does not meet length requirements.", Status.OK),
    PASSWORD_CHARS_UNSATISFIED(13, "Password does not meet character requirements.", Status.OK),
    USER_NOT_FOUND(14,"User not found.",Status.OK),
    EMAIL_ALREADY_IN_USE(16,"Email already in use.", Status.OK),
    REGISTER_SUCCESS(110, "User registered successfully.", Status.OK),
    LOGGED_IN_SUCCESSFULLY(120, "User logged in successfully.",Status.OK),
    SESSION_ACTIVE(130, "Session is active.", Status.OK),
    SESSION_EXPIRED(131, "Session is expired", Status.OK),
    SESSION_CLOSED(132, "Session is closed.",Status.OK),
    SESSION_REVOKED(133, "Session is revoked.",Status.OK),
    SESSION_NOT_FOUND(134, "Session not found.",Status.OK),
    SUFFICIENT_PLEVEL(140,"User has sufficient privilege level.",Status.OK),
    INSUFFICIENT_PLEVEL(141,"User has insufficient privilege level.",Status.OK),
    MOVIE_FOUND(210,"Found movie(s) with search parameters.", Status.OK),
    MOVIE_NOT_FOUND(211, "No movies found with search parameters.", Status.OK),
    PEOPLE_FOUND(212, "Found people with the search parameters.",Status.OK),
    PEOPLE_NOT_FOUND(213,"No people found with search parameters.", Status.OK);






    private final int    resultCode;
    private final String message;
    private final Status status;

    Result(int resultCode, String message, Status status){
        this.resultCode = resultCode;
        this.message = message;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public int getResultCode() {
        return resultCode;
    }
}
