package com.mateuszroszkowski.ServerManager;

import com.mateuszroszkowski.ServerManager.enumeration.Status;
import com.mateuszroszkowski.ServerManager.model.Server;
import com.mateuszroszkowski.ServerManager.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(ServerRepository serverRepository) {
//        String imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuEe49YU2lpWqkNMQQAa8ZqWaBFHF2Rd7Llg&usqp=CAU";
//        return args -> {
//            serverRepository.save(new Server(null, "192.168.1.160",
//                    "Ubuntu", "16GB", "Personal PC", imageUrl,
//                    Status.SERVER_UP));
//            serverRepository.save(new Server(null, "192.168.1.161",
//                    "Fedora", "16GB", "Dell tower", imageUrl,
//                    Status.SERVER_DOWN));
//            serverRepository.save(new Server(null, "192.168.1.162",
//                    "Ms 2008", "32GB", "Web server", imageUrl,
//                    Status.SERVER_UP));
//            serverRepository.save(new Server(null, "192.168.1.163",
//                    "Red Hat", "64GB", "Mail server", imageUrl,
//                    Status.SERVER_DOWN));
//        };
//    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
