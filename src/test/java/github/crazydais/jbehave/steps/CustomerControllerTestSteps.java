package github.crazydais.jbehave.steps;

import github.crazydais.data.entity.CustomerEntity;
import github.crazydais.rest.controller.CustomerController;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerControllerTestSteps {

    private CustomerController customerController;
    private HttpSession session;
    private List<CustomerEntity> expectedCustomerEntityList;
    private List<CustomerEntity> actualCustomerEntityList;
    private CustomerEntity expectedCustomerEntity;
    private CustomerEntity actualCustomerEntity;

    @BeforeScenario(uponType = ScenarioType.ANY)
    public void beforeScenario () {

        customerController = mock(CustomerController.class);
        session = mock(HttpSession.class);
        expectedCustomerEntityList = null;
        actualCustomerEntityList = null;
        expectedCustomerEntity = null;
        actualCustomerEntity = null;
    }

    private CustomerEntity getCustomer () {

        CustomerEntity c1 = new CustomerEntity();
        c1.setFirstName("Joey");
        c1.setLastName("Juju");
        return c1;
    }

    private List<CustomerEntity> getCustomers () {

        CustomerEntity c1 = new CustomerEntity();
        c1.setFirstName("Joey");
        c1.setLastName("Juju");

        CustomerEntity c2 = new CustomerEntity();
        c2.setFirstName("Jane");
        c2.setLastName("Doe");
        return Arrays.asList(c1, c2);
    }

    @Given("a CustomerController object will return a list of customers")
    public void getListOfCustomers () {

        expectedCustomerEntityList = getCustomers();
        when(customerController.getCustomers(session))
            .thenReturn(expectedCustomerEntityList);
    }

    @When("a getAll request is invoked")
    public void getAllCustomers () {

        actualCustomerEntityList = customerController.getCustomers(session);
    }

    @Then("all the customers are returned as expected")
    public void checkAllCustomers () {

        assertThat(actualCustomerEntityList, is(expectedCustomerEntityList));
        verify(customerController, times(1)).getCustomers(session);
    }

    @Given("a CustomerController object will return a single customer of $id")
    public void getSingleCustomer (Long id) {

        expectedCustomerEntity = getCustomer();
        when(customerController.getCustomerById(id))
            .thenReturn(expectedCustomerEntity);
    }

    @When("a getById request is invoked with the id of $id")
    public void getCustomerById (Long id) {

        actualCustomerEntity = customerController.getCustomerById(id);
    }

    @Then("a customer is returned as expected for $id")
    public void checkCustomer (Long id) {

        assertThat(actualCustomerEntity, is(expectedCustomerEntity));
        verify(customerController, times(1)).getCustomerById(id);
    }
}
