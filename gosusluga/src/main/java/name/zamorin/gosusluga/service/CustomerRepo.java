package name.zamorin.gosusluga.service;

import name.zamorin.gosusluga.model.Customer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Cacheable(value = "customers")
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @CachePut(value = "customers", key = "#customer.id")
    public Customer save(Customer customer);

    @Cacheable(value = "customers")
    public List<Customer> findAll();

    @CacheEvict(value = "customers", key = "#id")
    public void deleteById(Long id);

    @Cacheable(value = "customers", key = "#id")
    public Customer getOne(Long id);
}
