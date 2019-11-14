package name.zamorin.gosusluga;

import name.zamorin.gosusluga.model.Customer;
import name.zamorin.gosusluga.service.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GosuslugaApplication {
    @Autowired
    private CustomerRepo customerRepo;

    public static void main(String[] args) {
        SpringApplication.run(GosuslugaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Customer c = new Customer();
            c.setFirstName("Sergei");
            c.setLastName("Zamorin");
            c.setRequest("Job request");
            customerRepo.save(c);
        };
    }
}
