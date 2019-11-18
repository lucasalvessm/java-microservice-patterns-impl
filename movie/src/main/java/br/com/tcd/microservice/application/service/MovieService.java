package br.com.tcd.microservice.application.service;

import br.com.tcd.microservice.application.dto.MovieDTO;
import br.com.tcd.microservice.application.entity.Movie;

public interface MovieService {
    MovieDTO dtoFromMovie(Movie movie);
}
