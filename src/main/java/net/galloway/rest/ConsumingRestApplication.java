package net.galloway.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootApplication
@PropertySource("classpath:config.properties")
public class ConsumingRestApplication {

    private static final String API_KEY_HEADER = "X-CMC_PRO_API_KEY";

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.quotes.url}")
    private String apiUrl;

    @Value("${crypto.ids}")
    private String cryptoIds;

    private static final Logger log =
            LoggerFactory.getLogger(ConsumingRestApplication.class);

    public ConsumingRestApplication() {
    }

    public static void main(String[] args){
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(APPLICATION_JSON));
            headers.set(API_KEY_HEADER, apiKey);
            HttpEntity<?> request = new HttpEntity<>(headers);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("convert","EUR")
                    .queryParam("id",cryptoIds);

            ResponseEntity<String> response =
            restTemplate.exchange(builder.toUriString(), HttpMethod.GET,request, String.class);

            log.info(response.toString());
        };
    }
}
