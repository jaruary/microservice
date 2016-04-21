package com.example.webservice.controller;

import com.example.data.entity.Customer;
import com.example.data.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  CustomerRepository custRepo;

  // Create
  @RequestMapping(value = "/api/customer/add", method = RequestMethod.POST)
  public ResponseEntity<String> addCustomer(@RequestParam(value = "firstName", required = true) String fname, @RequestParam(value = "lastName", required = true) String lname) {
    try {
      Customer cust = new Customer();
      cust.setFirstName(fname);
      cust.setLastName(lname);
      this.custRepo.save(cust);
      return new ResponseEntity<>("{status: 'success'}", HttpStatus.OK);
    } catch (Exception ex) {
      // TODO : ex
      return new ResponseEntity<>("{status: 'failed'}", HttpStatus.BAD_REQUEST);
    }
  }

  // Read
  @RequestMapping(value = "/api/customer/get", method = RequestMethod.GET)
  public Customer getCustomerById(@RequestParam(value = "id", required = true, defaultValue = "0") Long id) {
    return custRepo.getOneById(id);
  }

  @RequestMapping(value = "/api/customer/getByFirstname", method = RequestMethod.GET)
  public List<Customer> getCustomerByFirstName(@RequestParam(value = "name", required = true, defaultValue = "") String fname) {
    return custRepo.findByFirstName(fname);
  }

  @RequestMapping(value = "/api/customer/getByLastname", method = RequestMethod.GET)
  public List<Customer> getCustomerByLastName(@RequestParam(value = "name", required = true, defaultValue = "") String lname) {
    return custRepo.findByLastName(lname);
  }

  @RequestMapping(value = "/api/customer/getAllCustomers", method = RequestMethod.GET)
  public List<Customer> getCustomers(@RequestParam(required = true, defaultValue = "true") Boolean all) {
    return custRepo.findAll();
  }

  // Update
  @RequestMapping(value = "/api/customer/update", method = RequestMethod.PUT)
  public ResponseEntity<String> updateCustomers(@RequestParam(value = "id", required = true) Long id, 
                                                @RequestParam(value = "firstName", required = true) String fname,
                                                @RequestParam(value = "lastName",required = true ) String lname) {
    try {
      Customer updateMe = custRepo.findOne(id);
      updateMe.setFirstName(fname);
      updateMe.setLastName(lname);
      custRepo.save(updateMe);
      return new ResponseEntity<>("{status: 'success'}", HttpStatus.OK);
    } catch (Exception ex) {
      // TODO : ex
      return new ResponseEntity<>("{status: 'failed'}", HttpStatus.BAD_REQUEST);
    }
  }

  // Delete
  @RequestMapping(value = "/api/customer/delete", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteCustomerById(@RequestParam(value = "id", required = true) Long id) {
    try {
      custRepo.delete(id);
      return new ResponseEntity<>("{status: 'success'}", HttpStatus.OK);
    } catch (Exception ex) {
      // TODO : ex
      return new ResponseEntity<>("{status: 'failed'}", HttpStatus.BAD_REQUEST);
    }
  }
}
