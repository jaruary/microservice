package github.crazydais.rest.controller;

import github.crazydais.data.entity.CustomerEntity;
import github.crazydais.data.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false, controllers = CustomerController.class)
public class CustomerControllerMockTest {

    private final Log LOGGER =
        LogFactory.getLog(CustomerControllerMockTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepo;

    private List<CustomerEntity> getCustomers() {

        List<CustomerEntity> customers = new ArrayList<>();

        CustomerEntity c1 = new CustomerEntity();
        c1.setFirstName("Ricky");
        c1.setLastName("Gervais");
        customers.add(c1);

        CustomerEntity c2 = new CustomerEntity();
        c2.setFirstName("Stephen");
        c2.setLastName("Merchant");
        customers.add(c2);

        CustomerEntity c3 = new CustomerEntity();
        c3.setFirstName("Karl");
        c3.setLastName("Pilkington");
        customers.add(c3);

        return customers;
    }

    @Test
    public void findAllCustomersTest() throws Exception {

        given(customerRepo.findAll()).willReturn(getCustomers());

        MvcResult result = this.mockMvc.perform(get("/api/customer/getAll")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(3))).andExpect(status().isOk())
            .andReturn();

        LOGGER.info("\n\n RESULT: " + result.getResponse().getContentAsString()
            + "\n\n");

    }

    @Test
    public void findCustomerByFirstNameTest() throws Exception {

        List<CustomerEntity> c = new ArrayList<>();
        c.add(getCustomers().get(0));

        given(customerRepo.findByFirstName("Ricky")).willReturn(c);

        this.mockMvc.perform(get("/api/customer/getByFirst")
            .param("name", "Ricky")
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("Ricky")))
            .andExpect(jsonPath("$[0].lastName", is("Gervais")));
    }

    @Test
    public void findCustomerByLastNameTest() throws Exception {

        List<CustomerEntity> c = new ArrayList<>();
        c.add(getCustomers().get(2));

        given(customerRepo.findByLastName("Pilkington")).willReturn(c);

        this.mockMvc.perform(get("/api/customer/getByLast")
            .param("name", "Pilkington").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("Karl")))
            .andExpect(jsonPath("$[0].lastName", is("Pilkington")));
    }


}
