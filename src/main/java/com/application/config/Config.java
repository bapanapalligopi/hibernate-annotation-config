package com.application.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan(basePackages = {
        "com.application.config",
        "com.application.hibernateutils",
        "com.application.models",
        "com.application.operations"
})
@PropertySource(value = "classpath:application.properties") // Load properties from application.properties
public class Config {

    // Inject properties from application.properties
    @Value("${hibernate.connection.url}")
    private String dbUrl;

    @Value("${hibernate.connection.username}")
    private String dbUsername;

    @Value("${hibernate.connection.password}")
    private String dbPassword;

    @Value("${hibernate.connection.driver_class}")
    private String dbDriver;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    // Define a bean for DataSource
    @Bean
    public DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(dbUrl); // Set the database URL
        dataSource.setUser(dbUsername); // Set the database username
        dataSource.setPassword(dbPassword); // Set the database password
        return dataSource; // Return the configured DataSource
    }
    
    // Define a bean for Hibernate properties
    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", dbDriver); // Set the driver class
        properties.setProperty("hibernate.show_sql", showSql); // Set the show_sql property
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto); // Set the hbm2ddl.auto property
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties; // Return the configured properties
        
    }
}
