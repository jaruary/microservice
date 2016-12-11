package github.crazydais.rest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

    private final Log LOGGER =
        LogFactory.getLog(CustomerControllerIntegrationTest.class);

    private final String BEARER =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkYXZlIiwiaWF0IjoxNDc2OTEzMzk1LCJleHAiOjE1MDg0NDkzOTUsImF1ZCI6IiIsInN1YiI6IiIsImtleSI6InZhbHVlIn0.LAzN0sGThzh7O9uJGdutmojLOZwPOkz4ySxA_u4j96Q";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllCustomers() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate
            .exchange("/api/customer/getAll", HttpMethod.GET, entity,
                String.class);
        String json = responseEntity.getBody();
        JSONArray customers = new JSONArray(json);
        JSONObject c1 = customers.getJSONObject(0);
        JSONObject c2 = customers.getJSONObject(1);
        JSONObject c3 = customers.getJSONObject(2);

        assertEquals("Joe", c1.get("firstName"));
        assertEquals("Alice", c2.get("firstName"));
        assertEquals("Harry", c3.get("firstName"));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
