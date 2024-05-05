package com.example.TDM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        try {
            // Attempt to execute a query to test the connection
            int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM parking", Integer.class);
            System.out.println("Database connection successful. Row count: " + rowCount);
        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            throw e;
        }
    }
}
