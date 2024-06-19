package lkm.starterproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "lkm.starterproject")
public class StarterprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterprojectApplication.class, args);
    }

}
