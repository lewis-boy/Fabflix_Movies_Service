package edu.uci.ics.luisae.service.movies.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;

import javax.ws.rs.core.Response;

public abstract class ResponseModel {
    @JsonIgnore
    private Result result;
    @JsonProperty(value = "message", required = true)
    private String message;
    @JsonProperty(value = "resultCode", required = true)
    private int resultCode;

    public ResponseModel() { }

    @JsonIgnore
    public ResponseModel(Result result)
    {
        this.result = result;
    }

    @JsonProperty("resultCode")
    public int getResultCode()
    {
        return result.getResultCode();
    }

    @JsonProperty("message")
    public String getMessage()
    {
        return result.getMessage();
    }

    @JsonIgnore
    public Result getResult()
    {
        return result;
    }

    @JsonIgnore
    public void setResult(Result result)
    {
        this.result = result;
    }

    @JsonIgnore
    public Response buildResponse()
    {
        ServiceLogger.LOGGER.info("Response being built with Result: " + result);
        if (result == null || result.getStatus() == Response.Status.INTERNAL_SERVER_ERROR)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(result.getStatus()).entity(this).build();
    }
    @JsonIgnore
    public Response buildResponseWithHeaders(Headers headers)
    {
        ServiceLogger.LOGGER.info("Response being built with Result: " + result);
        if (result == null || result.getStatus() == Response.Status.INTERNAL_SERVER_ERROR)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(result.getStatus()).entity(this).header("email",headers.getEmail()).
                header("session_id", headers.getSession_id()).header("transaction_id",headers.getTransaction_id()).build();
    }
}
