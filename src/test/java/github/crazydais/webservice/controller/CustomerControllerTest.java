package github.crazydais.webservice.controller;

import github.crazydais.Application;
import github.crazydais.data.repository.CustomerRepository;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("h2")
@WebAppConfiguration
@Transactional
public class CustomerControllerTest {
  
  @Autowired
  private WebApplicationContext wac;
  
  @Mock
  private CustomerRepository customerRepoMock;
  
  private MockMvc mockMvc;
   
  @Before
  public void setUp () {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }
  
  @Test
  public void findCustomerByName () throws Exception {
    this.mockMvc.perform(get("/api/customer/getById", "1L").accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print());
  }
  
}
