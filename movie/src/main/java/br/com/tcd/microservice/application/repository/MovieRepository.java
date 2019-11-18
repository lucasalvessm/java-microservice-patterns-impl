package br.com.tcd.microservice.application.repository;

import br.com.tcd.microservice.application.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
