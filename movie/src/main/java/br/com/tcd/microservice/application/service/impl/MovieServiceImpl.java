package br.com.tcd.microservice.application.service.impl;

import br.com.tcd.microservice.application.client.MovieGenresDiscoveryClient;
import br.com.tcd.microservice.application.dto.MovieDTO;
import br.com.tcd.microservice.application.entity.Movie;
import br.com.tcd.microservice.application.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {
    RestTemplate restTemplate = new RestTemplate();
   // @Autowired
    //private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MovieGenresDiscoveryClient movieGenresDiscoveryClient;

    public MovieDTO dtoFromMovie(Movie movie) {

        movieGenresDiscoveryClient.getGenreList("");

        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setYear(movie.getYear());
        dto.setGenreId(movie.getGenreId());
        return dto;
    }
}
