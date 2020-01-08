package edu.uci.ics.luisae.service.movies.core;

import edu.uci.ics.luisae.service.movies.Base.Headers;
import edu.uci.ics.luisae.service.movies.Base.Result;
import edu.uci.ics.luisae.service.movies.MoviesService;
import edu.uci.ics.luisae.service.movies.database.Intercommunication;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;
import edu.uci.ics.luisae.service.movies.models.*;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.*;
import edu.uci.ics.luisae.service.movies.utilities.Param;
import edu.uci.ics.luisae.service.movies.utilities.Util;

import javax.ws.rs.core.Response;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogicHandler {
    //TODO make sure every response build call is WITH HEADERS

    public static Response MovieIdHandler(Param[] params, Headers heads, MovieIdResponse response){
        People[] people;
        Genre[] genres;
        FullMovie movie;
        try{
            //get movie info
            PreparedStatement ps3 = Util.prepareStatement(QueryBuilder.buildMovieIdQuery(),params);
            ResultSet rs3 = ps3.executeQuery();
            if(!rs3.next()){
                ServiceLogger.LOGGER.info("no movies found");
                response.setResult(Result.MOVIE_NOT_FOUND);
                return response.buildResponseWithHeaders(heads);
            }
            movie = Util.modelMapper(rs3.getString("theFMovie"), FullMovie.class);
            if(movie.getHidden().booleanValue() && !Intercommunication.hasPrivilege(heads.getEmail(),4)) {
                response.setResult(Result.MOVIE_NOT_FOUND);
                return response.buildResponseWithHeaders(heads);
            }


            //get people info
            PreparedStatement ps = Util.prepareStatement(QueryBuilder.buildPeopleQuery(), params);
            ResultSet rs = ps.executeQuery();
            people = ClassBuilder.buildPeopleArray(rs);
            ServiceLogger.LOGGER.info(String.valueOf(people.length));

            //get genre info
            PreparedStatement ps2 = Util.prepareStatement(QueryBuilder.buildGenreQuery(), params);
            ResultSet rs2 = ps2.executeQuery();
            genres = ClassBuilder.buildGenreArray(rs2);
            ServiceLogger.LOGGER.info(String.valueOf(genres.length));

            //TODO catch all instances of when resultsets of genres and people are empty
        }
        catch(SQLException e){
            ServiceLogger.LOGGER.warning("In logic handler for movieid\n" + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        //combine them together
        movie.setGenres(genres);
        movie.setPeople(people);

        response.setFullMovie(movie);
        response.setResult(Result.MOVIE_FOUND);
        response.getFullMovie().print();
        return response.buildResponseWithHeaders(heads);
    }

    public static Response MovieSearchHandler(Headers heads, MovieSeachRequest request, MovieSearchResponse response){
        String query = QueryBuilder.buildMovieSearchQuery(request);
        ServiceLogger.LOGGER.info("Query: \n" + query);
        return lookForMovies(response,heads,request.getHidden(),query);
    }

    public static Response PhraseHandler(String[] phrases, Headers heads, MovieSearchResponse response, MovieSeachRequest request){
        String query = QueryBuilder.buildPhraseQuery(phrases, request);
        ServiceLogger.LOGGER.info("Query: \n" + query);
        return lookForMovies(response,heads,true,query);

    }

    public static Response ThumbnailHandler(Headers heads, ThumbnailResponse response, ThumbnailRequest request){
        String query = QueryBuilder.buildThumbnailQuery(request);
        ServiceLogger.LOGGER.info("Query: \n" + query);
        return lookForThumbnails(response, heads, true, query);
    }

    public static Response PeopleMovieHandler(Headers heads, PeopleRequest request, MovieSearchResponse response){
        String query = QueryBuilder.buildPeopleMovieQuery(request);
        ServiceLogger.LOGGER.info("Query: \n" + query);
        return lookForMovies(response, heads, true, query);
    }

    public static Response PeopleSearchHandler(Headers heads, PeopleSeachRequest request, PeopleSearchResponse response){
        String query = QueryBuilder.buildPeopleSearchQuery(request);
        ServiceLogger.LOGGER.info("Query: \n" + query);
        return lookforPeople(heads,response,query);
    }

    public static Response PeopleIdSearchHandler(Headers heads, PersonIdSearchResponse response, String id){
        Person person;
        String query = QueryBuilder.buildPersonIdSearchQuery();
        ServiceLogger.LOGGER.info("Query: \n" + query);
        try{
            PreparedStatement ps = MoviesService.getCon().prepareStatement(query);
            ps.setInt(1,Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                person = Util.modelMapper(rs.getString("person_info"), Person.class);
                response.setPerson(person);
                response.setResult(Result.PEOPLE_FOUND);
                return response.buildResponseWithHeaders(heads);
            }
            else{
                ServiceLogger.LOGGER.warning("no person found with id");
                response.setResult(Result.PEOPLE_NOT_FOUND);
                return response.buildResponseWithHeaders(heads);
            }

        }catch(SQLException e){ServiceLogger.LOGGER.info(e.getMessage());return response.buildResponseWithHeaders(heads);}
    }



    //TODO find out a way to merge lookForMovies and lookForThumbnails
    private static Response lookForMovies(MovieSearchResponse response, Headers heads, boolean wantsHidden, String query ){
        ServiceLogger.LOGGER.info("Entering lookForMovies");
        Movie[] movies;
        try{
            PreparedStatement ps = MoviesService.getCon().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ServiceLogger.LOGGER.info("movies found");
                movies = ClassBuilder.buildMovieArray(rs, wantsHidden, heads.getEmail());
                if(movies.length == 0)
                    response.setResult(Result.MOVIE_NOT_FOUND);
                else {
                    response.setMovies(movies);
                    response.setResult(Result.MOVIE_FOUND);
                }
            }
            else{
                ServiceLogger.LOGGER.info("no movies found");
                response.setResult(Result.MOVIE_NOT_FOUND);
                return response.buildResponseWithHeaders(heads);
            }

        }catch(SQLException e){
            ServiceLogger.LOGGER.warning("In logic handler for movieSearch\n" + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response.buildResponseWithHeaders(heads);
    }

    private static Response lookForThumbnails(ThumbnailResponse response, Headers heads, boolean wantsHidden, String query ){
        Thumbnail[] movies;
        try{
            PreparedStatement ps = MoviesService.getCon().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ServiceLogger.LOGGER.info("movies found");
                movies = ClassBuilder.buildThumbnailArray(rs, wantsHidden, heads.getEmail());
                if(movies.length == 0)
                    response.setResult(Result.MOVIE_NOT_FOUND);
                else {
                    response.setThumbnails(movies);
                    response.setResult(Result.MOVIE_FOUND);
                }
            }
            else{
                ServiceLogger.LOGGER.info("no movies found");
                response.setResult(Result.MOVIE_NOT_FOUND);
                return response.buildResponseWithHeaders(heads);
            }

        }catch(SQLException e){
            ServiceLogger.LOGGER.warning("In logic handler for movieSearch\n" + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response.buildResponseWithHeaders(heads);
    }

    private static Response lookforPeople(Headers heads, PeopleSearchResponse response, String query){
        Person[] people;
        try{
            PreparedStatement ps = MoviesService.getCon().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ServiceLogger.LOGGER.info("people found");
                people = ClassBuilder.buildPeopleSearchArray(rs);
                if(people.length == 0)
                    response.setResult(Result.PEOPLE_NOT_FOUND);
                else {
                    response.setPeople(people);
                    response.setResult(Result.PEOPLE_FOUND);
                }
            }
            else{
                ServiceLogger.LOGGER.info("no people found");
                response.setResult(Result.PEOPLE_NOT_FOUND);
                return response.buildResponseWithHeaders(heads);
            }

        }catch(SQLException e){
            ServiceLogger.LOGGER.warning("In logic handler for movieSearch\n" + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response.buildResponseWithHeaders(heads);
    }





}
