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
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "2000")},
            fallbackMethod = "getGenreListFallback")
    public List<GenreDTO> getGenreList(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("movie-genres-service");

        if (instances.size() == 0) {
            return null;
        }

        String serviceUri = String.format("%s/genre%s", instances.get(0).getUri(),
                organizationId);

        ResponseEntity<GenreDTO[]> res = restTemplate.getForEntity(serviceUri, GenreDTO[].class);

        return Arrays.asList(res.getBody());
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    private List<GenreDTO> getGenreListFallback(String organizationId) {
        List<GenreDTO> fallbackList = new ArrayList<>();
        return fallbackList;
    }
}
