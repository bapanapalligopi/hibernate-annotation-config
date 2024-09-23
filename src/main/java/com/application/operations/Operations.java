package com.application.operations;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.hibernateutils.Utils;
import com.application.models.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class provides operations for managing Customer entities in the database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class Operations {
	@Autowired
    private Utils utils; // Utility class for Hibernate session management

    /**
     * Inserts a new Customer into the database.
     *
     * @param customer the Customer object to be inserted
     */
    public void insertIntoTable(Customer customer) {
        Session session = utils.getSession(); // Obtain a Hibernate session
        session.beginTransaction(); // Start a new transaction
        session.save(customer); // Save the customer object to the database
        session.getTransaction().commit(); // Commit the transaction
        System.out.println("Row inserted successfully");
    }

    /**
     * Retrieves a Customer from the database by ID.
     *
     * @param id the ID of the Customer to retrieve
     * @return the Customer object, or null if not found
     */
    public Customer getCustomer(int id) {
        Session session = utils.getSession(); // Obtain a Hibernate session
        Customer customer2 = session.get(Customer.class, id); // Retrieve the customer by ID
        if (customer2 == null) return null; // Return null if no customer is found
        return customer2; // Return the retrieved Customer object
    }

    /**
     * Updates an existing Customer in the database.
     *
     * @param customer the Customer object containing updated data
     */
    public void updateIntoTable(Customer customer) {
        Session session = utils.getSession(); // Obtain a Hibernate session
        session.beginTransaction(); // Start a new transaction
        session.update(customer); // Update the customer object in the database
        session.getTransaction().commit(); // Commit the transaction
        System.out.println("The customer data updated successfully");
    }
}
