package br.com.tcd.microservice.application.controller;

import br.com.tcd.microservice.application.entity.Genre;
import br.com.tcd.microservice.application.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public ResponseEntity<Iterable<Genre>> listAll() {
        return ResponseEntity.ok(genreRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Genre> getOne(@PathVariable("id") Long genreId) {
        return ResponseEntity.ok(genreRepository.findOne(genreId));
    }

    @PostMapping
    public ResponseEntity<Genre> save(@Valid @RequestBody Genre req) {
        return ResponseEntity.ok(genreRepository.save(req));
    }
}
