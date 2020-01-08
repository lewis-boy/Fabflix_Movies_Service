package edu.uci.ics.luisae.service.movies.core;

import edu.uci.ics.luisae.service.movies.database.Intercommunication;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;
import edu.uci.ics.luisae.service.movies.models.MovieClasses.*;
import edu.uci.ics.luisae.service.movies.utilities.Util;

import java.lang.invoke.SerializedLambda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassBuilder {

    public static People[] buildPeopleArray(ResultSet rs) {
        ArrayList<People> peopleList = new ArrayList<>();
        try{
            while(rs.next()){
                People person = new People(rs.getString("name"), rs.getInt("person_id"));
                peopleList.add(person);
            }
        }catch(SQLException e){
            return null;
        }
        People[] peopleArray = new People[peopleList.size()];
        for(int i = 0; i < peopleList.size(); i++)
            peopleArray[i] = peopleList.get(i);
        return peopleArray;
    }

    public static Genre[] buildGenreArray(ResultSet rs){
        ArrayList<Genre> genreList = new ArrayList<>();
        try{
            while(rs.next()){
                Genre genre = new Genre(rs.getInt("genre_id"), rs.getString("name"));
                genreList.add(genre);
            }
        }catch(SQLException e){return null;}

        Genre[] genreArray = new Genre[genreList.size()];
        for(int i = 0; i < genreList.size(); i++)
            genreArray[i] = genreList.get(i);
        return  genreArray;
    }


    //TODO merge buildMovie and buildThumbnail to make them dynamic
    public static Movie[] buildMovieArray(ResultSet rs, boolean wantsHidden, String email){
        boolean hasPrivilege = false;
        boolean movieIsHidden;
        ArrayList<Movie> movieList = new ArrayList<>();
        ServiceLogger.LOGGER.info("TEST 1");
        if(wantsHidden)
            hasPrivilege = Intercommunication.hasPrivilege(email,4);
        ServiceLogger.LOGGER.info("TEST 2");
        try{
            rs.beforeFirst();
            //maybe here
            while(rs.next()){
                Movie movie = Util.modelMapper(rs.getString("imovie"),Movie.class);
                movieList.add(movie);
            }
        }catch(SQLException e){ServiceLogger.LOGGER.warning("result set problem");return null;}
        ServiceLogger.LOGGER.info("successfully built array list: " + movieList.size());
        Movie[] movieArray = new Movie[movieList.size()];
        for(int i = 0; i < movieList.size(); i++) {
            //HERE is the BUG
            movieIsHidden = movieList.get(i).getHidden();
            //if they dont have privilege, never show them fields
            if(skipMovie(wantsHidden,hasPrivilege,movieIsHidden))
                continue;
            if(!hasPrivilege)
                movieList.get(i).setHidden(null);
            movieArray[i] = movieList.get(i);
        }

        ServiceLogger.LOGGER.info("successfully built movie array: " + movieArray.length);
        return movieArray;
    }

    public static Thumbnail[] buildThumbnailArray(ResultSet rs, boolean wantsHidden, String email){
        boolean hasPrivilege = false;
        boolean movieIsHidden;
        ArrayList<Thumbnail> movieList = new ArrayList<>();
        ServiceLogger.LOGGER.info("TEST 1");
        if(wantsHidden)
            hasPrivilege = Intercommunication.hasPrivilege(email,4);
        ServiceLogger.LOGGER.info("TEST 2");
        try{
            rs.beforeFirst();
            //maybe here
            while(rs.next()){
                Thumbnail movie = Util.modelMapper(rs.getString("imovie"),Thumbnail.class);
                movieList.add(movie);
            }
        }catch(SQLException e){ServiceLogger.LOGGER.warning("result set problem: thumbnail");return null;}
        ServiceLogger.LOGGER.info("successfully built array list: " + movieList.size());
        Thumbnail[] movieArray = new Thumbnail[movieList.size()];
        for(int i = 0; i < movieList.size(); i++) {
            //HERE is the BUG
            movieIsHidden = movieList.get(i).getHidden();
            //if they dont have privilege, never show them fields
            if(skipMovie(wantsHidden,hasPrivilege,movieIsHidden))
                continue;
            if(!hasPrivilege)
                movieList.get(i).setHidden(null);
            movieArray[i] = movieList.get(i);
        }

        ServiceLogger.LOGGER.info("successfully built movie array: " + movieArray.length);
        return movieArray;
    }


    public static Person[] buildPeopleSearchArray(ResultSet rs){
        ArrayList<Person> personList = new ArrayList<>();
        try{
            rs.beforeFirst();
            while(rs.next()){
                Person person = Util.modelMapper(rs.getString("person_info"),Person.class);
                personList.add(person);
            }
        }catch(SQLException e){ServiceLogger.LOGGER.warning("result set problem: people");return null;}
        ServiceLogger.LOGGER.info("successfully built person array list: " + personList.size());
        Person[] personArray = new Person[personList.size()];
        for(int i = 0; i < personList.size();i++)
            personArray[i] = personList.get(i);
        ServiceLogger.LOGGER.info("successfully built person array: " + personArray.length);
        return personArray;
    }





    public static boolean skipMovie(boolean wantsHidden, boolean hasPrivilege, boolean movieIsHidden){
        if(movieIsHidden)
            if(!hasPrivilege || !wantsHidden)
                return true;

        return false;
    }

}
