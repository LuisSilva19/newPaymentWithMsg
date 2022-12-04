package db;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class MockDBConnection {

    public static PostgreSQLContainer postgreSQLContainer = PostgreSQLConfig.getInstance();

    static {
        postgreSQLContainer.start();
    }
}
