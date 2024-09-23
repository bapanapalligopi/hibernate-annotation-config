/**
 * Utility class for managing Hibernate sessions.
 */
package com.application.hibernateutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import com.application.models.Customer;

import jakarta.persistence.PostUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class provides methods to interact with Hibernate sessions.
 */
@Component
@Getter
@Setter
@Data
public class Utils {

    /**
     * Creates and returns a new Hibernate session.
     * 
     * @return a Hibernate Session object
     */
	@PostUpdate
	public void init() {
		getSession();
	}
    public static Session getSession() {
        // Create a new Configuration instance
        Configuration configuration = new Configuration();
       
      
        // Add the Customer entity class to the configuration
        configuration.addAnnotatedClass(Customer.class);
        
        // Build the SessionFactory from the configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        // Open a new session from the SessionFactory
        Session session = sessionFactory.openSession();
        
        // Return the newly created session
        return session;
    }
	
    
}
