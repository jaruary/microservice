Scenario: CustomerController getCustomers : when a getAll request is made, all the customers are returned as expected
Given a CustomerController object will return a list of customers
When a getAll request is invoked
Then all the customers are returned as expected

Scenario: CustomerController getCustomerById : when a getById request is made, a customer is returned as expected
Given a CustomerController object will return a single customer of 1
When a getById request is invoked with the id of 1
Then a customer is returned as expected for 1
