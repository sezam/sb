package name.zamorin.gosusluga;

import lombok.extern.slf4j.Slf4j;
import name.zamorin.gosusluga.model.Customer;
import name.zamorin.gosusluga.service.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Cacheable
@AutoConfigureMockMvc
class GosuslugaApplicationTests {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private MockMvc mock;

    @Test
    void contextLoads() {
    }

    @Test
    void testGet() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Sergei");
        customer.setLastName("Zamorin");
        customer.setRequest("Job request");
        customer = customerRepo.saveAndFlush(customer);
        Long key = customerRepo.count() - 1;

        mock.perform(get("/api/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.customers[" + key + "].firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$._embedded.customers[" + key + "].lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$._embedded.customers[" + key + "].request", is(customer.getRequest())));
    }

    @Test
    void testPost() throws Exception {
        mock.perform(post("/api/customers")
                .content("{\"firstName\":\"test1\", \"lastName\":\"test2\", \"request\": \"test3\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Autowired
    private CacheManager cacheManager;

    private void getAndPrint(Long id) {
        log.info("customer found: {}", customerRepo.getOne(id));
    }
}


