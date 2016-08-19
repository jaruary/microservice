package github.crazydais.webservice.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {
    
    private final Log log = LogFactory.getLog(CustomerController.class);
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void getAllCustomers() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity( "/api/customer/getAll", String.class, "");
        String json = responseEntity.getBody();
        JSONArray customers = new JSONArray(json);
        JSONObject c1 = customers.getJSONObject(0);
        JSONObject c2 = customers.getJSONObject(1);
        JSONObject c3 = customers.getJSONObject(2);
        
        assertEquals("Joe",     c1.get("firstName"));
        assertEquals("Alice",   c2.get("firstName"));
        assertEquals("Harry",   c3.get("firstName"));
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
}
