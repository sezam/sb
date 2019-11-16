package name.zamorin.gosusluga;

import com.google.common.cache.CacheBuilder;
import name.zamorin.gosusluga.model.Customer;
import name.zamorin.gosusluga.service.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

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

    @Bean("sezamCacheManager")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                .maximumSize(25)
                                .expireAfterAccess(3, TimeUnit.SECONDS)
                                .build().asMap(),
                        false);
            }
        };
    }
}
