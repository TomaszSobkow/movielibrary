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
}