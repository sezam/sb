package name.zamorin.gosusluga.service;

import name.zamorin.gosusluga.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
