package github.crazydais.webservice.controller;

import github.crazydais.Application;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("h2")
@WebAppConfiguration
@Transactional
public class CustomerControllerTest {

  @Autowired
  WebApplicationContext wac;

  MockMvc mockMvc;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void findAllCustomersTest() throws Exception {
    this.mockMvc.perform(get("/api/customer/getAll")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("Joe")))
            .andExpect(jsonPath("$[0].lastName", is("Bloggs")));
  }

  @Test
  public void findCustomerByIdTest() throws Exception {
    this.mockMvc.perform(get("/api/customer/getById")
            .param("id", "2")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(2)))
            .andExpect(jsonPath("$.firstName", is("Alice")))
            .andExpect(jsonPath("$.lastName", is("Doe")));
  }

  @Test
  public void findCustomerByNameTest() throws Exception {
    this.mockMvc.perform(get("/api/customer/getByFirst")
            .param("name", "Harry")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(3)))
            .andExpect(jsonPath("$[0].firstName", is("Harry")))
            .andExpect(jsonPath("$[0].lastName", is("Wizard")));
  }

}
