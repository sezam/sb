package name.zamorin.gosusluga;

import name.zamorin.gosusluga.model.Customer;
import name.zamorin.gosusluga.service.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.CoreMatchers.*;


@SpringBootTest
class GosuslugaApplicationTests {
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private MockMvc mock;

	@Test
	void contextLoads() {
	}

	@Test
	void testCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("Sergei");
		customer.setLastName("Zamorin");
		customer.setRequest("Job request");
		customerRepo.saveAndFlush(customer);
		Long key = customerRepo.count() - 1;

		mock.perform(get("/customer"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.customer[" + key + "].firstName", is(customer.getFirstName())))
				.andExpect(jsonPath("$._embedded.customer[" + key + "].lastName", is(customer.getLastName())))
				.andExpect(jsonPath("$._embedded.customer[" + key + "].request", is(customer.getRequest())));
	}
}


