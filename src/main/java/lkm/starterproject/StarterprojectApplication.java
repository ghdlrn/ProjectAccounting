package lkm.starterproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "lkm.starterproject.accounting.mapper")
public class StarterprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterprojectApplication.class, args);
    }

}
