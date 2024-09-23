package com.application.testapplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.application.config.Config;
import com.application.hibernateutils.Utils;
import com.application.models.Customer;
import com.application.operations.Operations;

/**
 * Main class to demonstrate the functionality of the application.
 */
public class Main {
    public static void main(String[] args) {
        // Load the Spring application context from the config.xml file
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(Config.class);
        
        // Retrieve a Customer bean from the application context
        Customer customer = applicationContext.getBean("customer", Customer.class);
        
        // Retrieve the Utils bean for session management
        Utils utils = applicationContext.getBean("utils", Utils.class);
        
        // Retrieve the Operations bean to perform database operations
        Operations operations = applicationContext.getBean("operations", Operations.class);
        
        // Print the initial state of the customer object
        System.out.println("The customer object is: " + customer);
        System.out.println("the utils object is; "+utils);
        System.out.println("the operations object is;"+operations);
        
        // Set values for the customer object
        customer.setName("asif");
        customer.setAddress("vij");
        customer.setPhoneNumber("282872827");
        
        // Uncomment the next line to insert the customer into the database
         operations.insertIntoTable(customer);
        
        // Retrieve a customer by ID (1)
        Customer customer2 = operations.getCustomer(1);
        if (customer2 == null) {
            System.out.println("The customer is not found!!");
        } else {
            // Print the retrieved customer data
            System.out.println("The customer data is: " + customer2);
        }
        
        // Retrieve another customer by ID (2) for updating
        Customer upCustomer = operations.getCustomer(2);
        upCustomer.setAddress("Hyderabad"); // Update the address
        operations.updateIntoTable(upCustomer); // Persist the updated customer data
    }
}
