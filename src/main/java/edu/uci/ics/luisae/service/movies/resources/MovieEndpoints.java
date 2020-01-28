package edu.uci.ics.luisae.service.movies.resources;

import edu.uci.ics.luisae.service.movies.core.LogicHandler;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;
import edu.uci.ics.luisae.service.movies.models.*;
import edu.uci.ics.luisae.service.movies.Base.Headers;
import edu.uci.ics.luisae.service.movies.models.MovieRandomResponse;
import edu.uci.ics.luisae.service.movies.models.ThumbnailRequest;
import edu.uci.ics.luisae.service.movies.models.ThumbnailResponse;
import edu.uci.ics.luisae.service.movies.utilities.Param;
import edu.uci.ics.luisae.service.movies.utilities.Util;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Types;

@Path("movies")
public class MovieEndpoints {

    @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@Context HttpHeaders headers,
                            @QueryParam("title") String title,
                            @QueryParam("year") Integer year,
                            @QueryParam("director") String director,
                            @QueryParam("genre") String genre,
                            @QueryParam("hidden") Boolean hidden,
                            @QueryParam("limit") Integer limit,
                            @QueryParam("offset") Integer offset,
                            @QueryParam("orderby") String orderby,
                            @QueryParam("direction") String direction){
        ServiceLogger.LOGGER.info("entering search");
        MovieSearchResponse response = new MovieSearchResponse();
        MovieSeachRequest request = new MovieSeachRequest(
                title,
                year,
                director,
                genre,
                hidden,
                limit,
                offset,
                orderby,
                direction);
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
//        ArrayList<String> a = request.buildStringArrayList();
//        for(String s : a)
//            ServiceLogger.LOGGER.info(s);
//        ServiceLogger.LOGGER.info(QueryBuilder.buildMovieSearchQuery(request.buildStringArrayList(),request));
        return LogicHandler.MovieSearchHandler(heads, request, response);



        //return Response.status(Response.Status.OK).build();
    }


    @Path("get/{movie_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieId(@Context HttpHeaders headers,
                               @PathParam("movie_id") String movie_id){
        MovieIdResponse response = new MovieIdResponse();
        //could of done this with just a string but when its for ints or something else use ? and set it
        Param[] params = new Param[]{
                Param.create(Types.VARCHAR, movie_id)
        };
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));

        return LogicHandler.MovieIdHandler(params, heads, response);
    }

    //todo test through api
    @Path("browse/{phrase}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response phrase(@Context HttpHeaders headers,
                           @QueryParam("limit") Integer limit,
                           @QueryParam("offset") Integer offset,
                           @QueryParam("orderby") String orderby,
                           @QueryParam("direction") String direction,
                           @PathParam("phrase")String phrase){
        MovieSeachRequest request = new MovieSeachRequest(
                null,
                null,
                null,
                null,
                null,
                limit,
                offset,
                orderby,
                direction);
        MovieSearchResponse response = new MovieSearchResponse();
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
        ServiceLogger.LOGGER.info(phrase);
        String[] phrases = phrase.split(",");

        return LogicHandler.PhraseHandler(phrases, heads, response, request);
    }

    @Path("thumbnail")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response thumbnail(@Context HttpHeaders headers, String jsonText){
        ServiceLogger.LOGGER.info(jsonText);
        ThumbnailResponse response = new ThumbnailResponse();
        ThumbnailRequest request = Util.modelMapper(jsonText, ThumbnailRequest.class);
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
        if(request == null){
            ServiceLogger.LOGGER.warning("request was null");
            return response.buildResponseWithHeaders(heads);
        }
        ServiceLogger.LOGGER.info("All models made successfully");

        return LogicHandler.ThumbnailHandler(heads, response, request);
    }



    @Path("people")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeople(@Context HttpHeaders headers,
                              @QueryParam(value = "name")String name,
                              @QueryParam(value = "limit")Integer limit,
                              @QueryParam(value = "offset")Integer offset,
                              @QueryParam(value = "orderby")String orderby,
                              @QueryParam(value = "direction")String direction){
        PeopleRequest request = new PeopleRequest(
                name,
                limit,
                offset,
                orderby,
                direction);
        MovieSearchResponse response = new MovieSearchResponse();
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
        request.print();
        return LogicHandler.PeopleMovieHandler(heads, request, response);

    }

    @Path("people/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response peoplesearch(@Context HttpHeaders headers,
                                 @QueryParam(value = "name") String name,
                                 @QueryParam(value = "birthday") String birthday,
                                 @QueryParam(value = "movie_title") String movie_title,
                                 @QueryParam(value = "limit") Integer limit,
                                 @QueryParam(value = "offset") Integer offset,
                                 @QueryParam(value = "orderby") String orderby,
                                 @QueryParam(value = "direction") String direction){
        PeopleSeachRequest request = new PeopleSeachRequest(name,
                birthday,
                movie_title,
                limit,
                offset,
                orderby,
                direction);
        PeopleSearchResponse response = new PeopleSearchResponse();
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
        return LogicHandler.PeopleSearchHandler(heads, request, response);
    }

    @Path("people/get/{person_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonId(@Context HttpHeaders headers,
                                @PathParam(value = "person_id") String person_id){
        PersonIdSearchResponse response = new PersonIdSearchResponse();
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
        return LogicHandler.PeopleIdSearchHandler(heads, response, person_id);
    }

    @Path("random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomMovies(@Context HttpHeaders headers,
                                    @QueryParam("genre") String genre,
                                    @QueryParam("limit") Integer limit){
        MovieRandomRequest request = new MovieRandomRequest(genre,limit);
        MovieRandomResponse response = new MovieRandomResponse();
        Headers heads = new Headers();
        heads.setEmail(headers.getHeaderString("email"));
        heads.setSession_id(headers.getHeaderString("session_id"));
        heads.setTransaction_id(headers.getHeaderString("transaction_id"));
        return LogicHandler.MovieRandomHandler(heads, request, response);
    }




    @Path("test")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test(String jsonText){
        //test with json types how to deserialize objects
        ServiceLogger.LOGGER.info(jsonText);
        TestDeserializeClass testClass = Util.modelMapper(jsonText, TestDeserializeClass.class);
        if(testClass == null)
            ServiceLogger.LOGGER.warning("FAILED");
        else
            ServiceLogger.LOGGER.info("PASSED");
        return Response.status(Response.Status.OK).build();
    }


}
