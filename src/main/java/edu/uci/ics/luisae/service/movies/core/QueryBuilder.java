package edu.uci.ics.luisae.service.movies.core;

import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;
import edu.uci.ics.luisae.service.movies.models.*;

public class QueryBuilder {
    //TODO test this query out on perhaps datagrip

    public static String buildMovieSearchQuery(MovieSeachRequest request){
        String select = "SELECT JSON_OBJECT('movie_id', n.movie_id,\n" +
                "    'title', n.title,\n" +
                "    'year', n.year,\n" +
                "    'director',n.director,\n" +
                "    'rating', n.rating,\n" +
                "    'backdrop_path', n.backdrop_path,\n" +
                "    'poster_path', n.poster_path,\n" +
                "    'hidden', n.hidden)as imovie";
        String from;
        if(request.getGenre() == null)
            from = " FROM (" +  buildGeneralMovie() + ") as n";
        else
            from = " FROM (" + buildGeneralMovieWithGenre() + ")as n";
        String where = " WHERE 1=1";
        String orderby = " ORDER BY " + request.getOrderby() + " " + request.getDirection()
                + ", " + request.getSecondaryOrder() + " " + request.getSecondaryDirection();
        String limit = " LIMIT " + request.getLimit() + " OFFSET " + request.getOffset();

        /*
        for(String field : fields)
            select += (", " + field);

         */
        if(request.getTitle() != null) {
            where += " && title LIKE '%" + request.getTitle() + "%'";
        }
        if(request.getYear() != null) {
            where += " && year = " + request.getYear();
        }
        if(request.getDirector() != null) {
            where += " && director LIKE '%" + request.getDirector() + "%'";
        }
        if(request.getGenre() != null) {
            where += " && genres LIKE '%" + request.getGenre() + "%'";
        }


        ServiceLogger.LOGGER.info((select + from + where + orderby + limit + ";").replace("\"",""));
        return (select + from + where + orderby + limit + ";").replace("\"","");
    }

    public static String testQuery(){
        return
                " select JSON_OBJECT(\n" +
                "                'movie_id',almostMovie.movie_id,\n" +
                "                'title',almostMovie.title,\n" +
                "                'year',almostMovie.year,\n" +
                "                'director',almostMovie.director,\n" +
                "                'rating',almostMovie.rating,\n" +
                "                'num_votes',almostMovie.num_votes,\n" +
                "                'budget',almostMovie.budget,\n" +
                "                'revenue',almostMovie.revenue,\n" +
                "                'overview',almostMovie.overview,\n" +
                "                'backdrop_path',almostMovie.backdrop_path,\n" +
                "                'poster_path',almostMovie.poster_path,\n" +
                "                'hidden',almostMovie.hidden,\n" +
                "                'genres',almostMovie.genres,\n" +
                "                'people',almostPeople.people) as tMovie\n" +
                "    FROM (SELECT fullMovie.movie_id,fullMovie.title,fullMovie.year,fullMovie.director,fullMovie.rating,fullMovie.num_votes,\n" +
                "                     fullMovie.budget,fullMovie.revenue,fullMovie.overview,fullMovie.backdrop_path,fullMovie.poster_path,fullMovie.hidden,\n" +
                "                     fullGenre.genres\n" +
                "                FROM(select theMovie.movie_id,theMovie.title,theMovie.year,d.name as director,theMovie.rating,theMovie.num_votes,\n" +
                "                theMovie.budget,theMovie.revenue,theMovie.overview,theMovie.backdrop_path,theMovie.poster_path,theMovie.hidden\n" +
                "            FROM (select m.movie_id,m.title,m.year,m.director_id,m.rating,m.num_votes,m.budget,m.revenue,m.overview,m.backdrop_path,m.poster_path,m.hidden\n" +
                "            FROM movie as m WHERE m.movie_id = 'tt0031022') as theMovie JOIN\n" +
                "            person as d ON theMovie.director_id = d.person_id)\n" +
                "                as fullMovie JOIN\n" +
                "            (select oneGenreMap.movie_id,\n" +
                "                    CONCAT('[',GROUP_CONCAT(JSON_OBJECT('genre_id',g.genre_id,'name',g.name)),']')as genres\n" +
                "             FROM (select * FROM genre_in_movie WHERE genre_in_movie.movie_id = 'tt0031022')\n" +
                "                as oneGenreMap\n" +
                "            JOIN genre as g ON oneGenreMap.genre_id = g.genre_id)\n" +
                "                as fullGenre ON fullMovie.movie_id = fullGenre.movie_id) as almostMovie JOIN\n" +
                "        (select onePersonMap.movie_id,\n" +
                "               CONCAT('[',GROUP_CONCAT(JSON_OBJECT('person_id',p.person_id,'name',p.name)),']')as people\n" +
                "        FROM (select * FROM person_in_movie WHERE person_in_movie.movie_id = 'tt0031022')as onePersonMap JOIN\n" +
                "        person as p ON onePersonMap.person_id = p.person_id) as almostPeople ON almostMovie.movie_id = almostPeople.movie_id;";
    }

    public static String buildPersonQuery(String[] keywords){
        String select = "SELECT *";
        String from = " FROM movie";
        String where = " WHERE 1=1";
        //TODO
        return null;
    }

    public static String buildPeopleQuery(){
        String query = "select p.person_id, p.name\n" +
                "        FROM (select * FROM person_in_movie WHERE person_in_movie.movie_id = ?)as onePersonMap JOIN\n" +
                "        person as p ON onePersonMap.person_id = p.person_id";
        return query;
    }

    public static String buildGenreQuery(){
        String query = "select g.genre_id,g.name\n" +
                "                FROM (select * FROM genre_in_movie WHERE genre_in_movie.movie_id = ?)\n" +
                "                as oneGenreMap\n" +
                "                JOIN genre as g ON oneGenreMap.genre_id = g.genre_id;";
        return query;
    }

    public static String buildMovieIdQuery(){
        String query = "select JSON_OBJECT(\n" +
                "    'movie_id', fullMovie.movie_id,\n" +
                "    'title', fullMovie.title,\n" +
                "    'year', fullMovie.year,\n" +
                "    'director', fullMovie.director,\n" +
                "    'rating', fullMovie.rating,\n" +
                "    'num_votes', fullMovie.num_votes,\n" +
                "    'budget', fullMovie.budget,\n" +
                "    'revenue', fullMovie.revenue,\n" +
                "    'overview', fullMovie.overview,\n" +
                "    'backdrop_path', fullMovie.backdrop_path,\n" +
                "    'poster_path', fullMovie.poster_path,\n" +
                "    'hidden', fullMovie.hidden\n" +
                "           )as theFMovie FROM\n" +
                "(select theMovie.movie_id,theMovie.title,theMovie.year,d.name as director,theMovie.rating,theMovie.num_votes,\n" +
                "                 theMovie.budget,theMovie.revenue,theMovie.overview,theMovie.backdrop_path,theMovie.poster_path,theMovie.hidden\n" +
                "                FROM (select m.movie_id,m.title,m.year,m.director_id,m.rating,m.num_votes,m.budget,m.revenue,m.overview,m.backdrop_path,m.poster_path,m.hidden\n" +
                "                FROM movie as m WHERE m.movie_id = ?) as theMovie JOIN\n" +
                "                person as d ON theMovie.director_id = d.person_id) as fullMovie;";
        return query;
    }

    //General Movies come already with movie + person in order to get directors name

    public static String buildGeneralMovieWithGenre(){
        String query = "SELECT m.movie_id,m.title,m.year,m.director,m.rating,m.num_votes,m.budget,m.revenue,m.overview,m.backdrop_path,m.poster_path,\n" +
                "           m.hidden, genremap.genres as genres\n" +
                "           FROM (SELECT mov.movie_id,mov.title,mov.year,p.name as director,mov.rating,mov.num_votes,mov.budget,mov.revenue,mov.overview,\n" +
                "               mov.backdrop_path,mov.poster_path,mov.hidden FROM movie as mov JOIN person as p ON mov.director_id = p.person_id) as m\n" +
                "           JOIN(SELECT gim.movie_id, group_concat(distinct g.name) as genres FROM genre_in_movie as gim JOIN genre g ON gim.genre_id = g.genre_id\n" +
                "                group by gim.movie_id) as genremap\n" +
                "            ON m.movie_id = genremap.movie_id";
        return query;
    }

    public static String buildGeneralMovie(){
        String query = "SELECT m.movie_id,m.title,m.year,m.director,m.rating,m.num_votes,m.budget,m.revenue,m.overview,m.backdrop_path,m.poster_path,\n" +
                "           m.hidden\n" +
                "           FROM (SELECT mov.movie_id,mov.title,mov.year,p.name as director,mov.rating,mov.num_votes,mov.budget,mov.revenue,mov.overview,\n" +
                "               mov.backdrop_path,mov.poster_path,mov.hidden FROM movie as mov JOIN person as p ON mov.director_id = p.person_id)as m";
        return query;
    }

    public static String buildPhraseQuery(String[] phrases, MovieSeachRequest request){
        String beginning = "select JSON_OBJECT('movie_id', full_movies.movie_id,\n" +
                "     'title', full_movies.title,\n" +
                "     'year', full_movies.year,\n" +
                "     'director',p.name,\n" +
                "     'rating', full_movies.rating,\n" +
                "     'backdrop_path', full_movies.backdrop_path,\n" +
                "     'poster_path', full_movies.poster_path,\n" +
                "     'hidden', full_movies.hidden)as imovie\n" +
                "from\n" +
                "    (select words.movie_id,words.phrases, m.title,m.year,m.director_id,m.rating,m.num_votes, m.budget,m.revenue,m.overview,m.backdrop_path,\n" +
                "            m.poster_path, m.hidden\n" +
                "            from (select km.movie_id, GROUP_CONCAT(DISTINCT k.name order by k.name) as phrases\n" +
                "            FROM keyword_in_movie as km JOIN keyword as k ON km.keyword_id = k.keyword_id\n" +
                "            group by km.movie_id\n" +
                "            order by km.movie_id) as words\n" +
                "                JOIN movie as m ON words.movie_id = m.movie_id\n" +
                "        WHERE 1=1";
        String middle = "";
        String ending = ") as full_movies\n" +
                "        JOIN person as p ON p.person_id = full_movies.director_id";
        String orderby = " ORDER BY " + request.getOrderby() + " " + request.getDirection()
                + ", " + request.getSecondaryOrder() + " " + request.getSecondaryDirection();
        String limit = " LIMIT " + request.getLimit() + " OFFSET " + request.getOffset();
        for(String phrase: phrases)
            middle += " AND phrases LIKE '%" + phrase + "%'";
        return (beginning + middle + ending + orderby + limit + ";");
    }

    public static String buildThumbnailQuery(ThumbnailRequest request){
        String query = "select JSON_OBJECT('movie_id', m.movie_id,\n" +
                "     'title', m.title,\n" +
                "     'backdrop_path', m.backdrop_path,\n" +
                "     'poster_path', m.poster_path, 'hidden', m.hidden)as imovie\n" +
                "from movie as m where 1=0";
        String middle = "";
        for(String movieId : request.getMovie_ids())
            middle += " || m.movie_id = '" + movieId +"'";
        return query + middle + ";";
    }

    public static String buildPeopleMovieQuery(PeopleRequest request){
        String begining = "select JSON_OBJECT('movie_id', full_movies.movie_id,\n" +
                "     'title', full_movies.title,\n" +
                "     'year', full_movies.year,\n" +
                "     'director',full_movies.director,\n" +
                "     'rating', full_movies.rating,\n" +
                "     'backdrop_path', full_movies.backdrop_path,\n" +
                "     'poster_path', full_movies.poster_path,\n" +
                "     'hidden', full_movies.hidden)as imovie from\n" +
                "    (select * from (SELECT pim.movie_id as pid from\n" +
                "        (SELECT p.person_id FROM person as p where p.name LIKE '%";
        String ending = "%')as filterP JOIN\n" +
                "        person_in_movie as pim on filterP.person_id=pim.person_id)as moviesIds JOIN\n" +
                "         (SELECT mov.movie_id,mov.title,mov.year,p.name as director,mov.rating,mov.num_votes,mov.budget,\n" +
                "                 mov.revenue,mov.overview,mov.backdrop_path,mov.poster_path,mov.hidden FROM movie as mov JOIN\n" +
                "                person as p ON mov.director_id = p.person_id)as movieD ON movieD.movie_id = moviesIds.pid) as full_movies";
        String orderby = " ORDER BY " + request.getOrderby() + " " + request.getDirection();
        String limit = " LIMIT " + request.getLimit() + " OFFSET " + request.getOffset();
        return begining + request.getName() + ending + orderby + limit + ";";
    }

    public static String buildPeopleSearchQuery(PeopleSeachRequest request){
        String movie = "";
        String where = " where 1=1";
        String beginning = "select JSON_OBJECT('person_id',p.person_id,\n" +
                "    'name',p.name,\n" +
                "    'birthday',p.birthday,\n" +
                "    'popularity',p.popularity,\n" +
                "    'profile_path',p.profile_path) as person_info\n" +
                "from person as p";
        if(request.getMovie_title() != null)
        {
            movie = " join (select distinct map.person_id from (select m.title, m.movie_id from movie as m where m.title LIKE '%"+
                    request.getMovie_title() +"%') as titles\n" +
                    "    join (select pim.person_id,GROUP_CONCAT(distinct pim.movie_id) as movies from person_in_movie as pim group by pim.person_id)\n" +
                    "        as map on find_in_set(titles.movie_id, map.movies)!=0)as extra on p.person_id=extra.person_id";
        }
        String orderby = " ORDER BY " + request.getOrderby() + " " + request.getDirection()
                + ", " + request.getSecondaryOrder() + " " + request.getSecondaryDirection();
        String limit = " LIMIT " + request.getLimit() + " OFFSET " + request.getOffset();
        if(request.getBirthday() != null)
            where += " && p.birthday like '%" + request.getBirthday() + "%'";
        if(request.getName() != null)
            where += " && p.name like '%" + request.getName() + "%'";

        return beginning + movie + where + orderby + limit + ";";
    }

    public static String buildPersonIdSearchQuery(){
        String query = "select JSON_OBJECT('person_id',p.person_id,'name',p.name,'gender',g.gender_name,'birthday',p.birthday,'biography',p.biography,'birthplace',p.birthplace,\n" +
                "       'popularity',p.popularity,'profile_path',p.profile_path)as person_info from\n" +
                "              (select * from person where person_id = ?) as p JOIN gender as g on p.gender_id=g.gender_id;";
        return query;
    }

    public static String buildRandomMovieQuery(MovieRandomRequest request){
        String query = "select JSON_OBJECT('movies',JSON_ARRAYAGG(movies)) as Themovies from\n" +
                "        (select JSON_OBJECT('title',movie.title,\n" +
                "                            'year',movie.year,\n" +
                "                            'stars',extra_info.stars,\n" +
                "                            'poster_path',movie.poster_path,\n" +
                "                            'backdrop_path',movie.backdrop_path) as movies from movie join\n" +
                "            (select people.movie_id,people.stars,genres.genres from\n" +
                "                (select pim.movie_id, GROUP_CONCAT(distinct CONCAT(person.name,':',person.profile_path))as stars\n" +
                "                from person_in_movie as pim join person on pim.person_id = person.person_id group by pim.movie_id) as people\n" +
                "                    JOIN\n" +
                "                (select gim.movie_id, GROUP_CONCAT(distinct genre.name)as genres\n" +
                "                from genre_in_movie as gim join genre on gim.genre_id = genre.genre_id group by gim.movie_id) as genres\n" +
                "            ON people.movie_id=genres.movie_id) as extra_info\n" +
                "        on movie.movie_id=extra_info.movie_id";
        String where = request.getGenre()==null?"":" where extra_info.genres like '%"+request.getGenre()+"%' ";
        String orderby = "ORDER BY RAND()";
        String limit = " limit " + request.getLimit();
        String end = ") as test;";

        return query + where + orderby + limit + end;
    }






}
