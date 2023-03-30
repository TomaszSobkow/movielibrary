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
}
