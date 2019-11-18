package br.com.tcd.microservice.application.repository;

import br.com.tcd.microservice.application.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
