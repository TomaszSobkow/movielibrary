package ie.fixit.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    MovieController movieController;


    public Movie getById(int id){
        return jdbcTemplate.queryForObject("select id, title,rating FROM movies WHERE id=?",
                BeanPropertyRowMapper.newInstance(Movie.class), id);

    }
    public List<Movie> getAll(){
          List<Movie> query = jdbcTemplate.query("select id,title,rating from movies",
                BeanPropertyRowMapper.newInstance(Movie.class));
//
////        or return jdbcTemplate.query("select id,title,rating from movie",
////                        BeanPropertyRowMapper.newInstance(Movie.class));
        return query;
    }

    public int save(Movie newMovie) {
        return jdbcTemplate.update("INSERT INTO movies(title, rating) values(?,?)",
                newMovie.getTitle(), newMovie.getRating());
    }

    public int update(Movie movie){
       return jdbcTemplate.update("UPDATE movies SET title=?, rating=? WHERE id=?",
        movie.getTitle(), movie.getRating(), movie.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM movies WHERE id=?",id);
    }
}
