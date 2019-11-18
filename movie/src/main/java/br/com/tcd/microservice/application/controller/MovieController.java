package br.com.tcd.microservice.application.controller;

import br.com.tcd.microservice.application.dto.MovieDTO;
import br.com.tcd.microservice.application.entity.Movie;
import br.com.tcd.microservice.application.repository.MovieRepository;
import br.com.tcd.microservice.application.service.MovieService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

    @GetMapping
    @HystrixCommand(
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")})
    public ResponseEntity<List<MovieDTO>> listAll() {
        List<Movie> movieList = (List) movieRepository.findAll();
        return ResponseEntity.ok(movieList.stream().map(movie -> movieService.dtoFromMovie(movie)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@Valid @RequestBody Movie req) {
        return ResponseEntity.ok(movieRepository.save(req));
    }
}
