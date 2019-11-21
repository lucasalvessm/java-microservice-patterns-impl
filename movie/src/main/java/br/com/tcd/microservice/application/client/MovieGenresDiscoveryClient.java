package br.com.tcd.microservice.application.client;

import br.com.tcd.microservice.application.dto.GenreDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MovieGenresDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "50")},
            fallbackMethod = "getGenreFallback")
    public GenreDTO getGenre(Long genreId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("movie-genres-service");

        if (instances.size() == 0) {
            throw new IllegalArgumentException("Could not get movie-genres host information");
        }

        String serviceUri = instances.get(0).getUri() +  "/genre/" + genreId;

        ResponseEntity<GenreDTO> res = restTemplate.getForEntity(serviceUri, GenreDTO.class);

        return res.getBody();
    }

    private GenreDTO getGenreFallback(Long genreId) {
        return new GenreDTO(genreId, "Without enough information");
    }
}
