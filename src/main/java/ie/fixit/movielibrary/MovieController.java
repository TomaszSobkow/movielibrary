package ie.fixit.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getByID(@PathVariable("id") int id){
        return movieRepository.getById(id);
    }

    @PostMapping("")
    public int save(@RequestBody Movie newMovie){
        return  movieRepository.save(newMovie);
    }

    //Whole object has been updated all fields in the Object will by overwriten
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
            Movie movie = movieRepository.getById(id);
            if(movie!= null){
                movie.setTitle(updatedMovie.getTitle());
                movie.setRating(updatedMovie.getRating());
                movieRepository.update(movie);
                return 1;
            }else {
                return -1;
            }
    }

    @PatchMapping("/{id}")
    public int updatePartially(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
       Movie movie= movieRepository.getById(id);
       if(movie != null){
           if(updatedMovie.getTitle() != null) movie.setTitle(updatedMovie.getTitle());
           if(updatedMovie.getRating() != null) movie.setRating(updatedMovie.getRating());
           movieRepository.update(movie);
           return  1;
       }else
           return -1;
    }

}
