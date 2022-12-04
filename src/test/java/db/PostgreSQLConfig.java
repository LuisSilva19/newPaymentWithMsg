package db;

import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLConfig extends PostgreSQLContainer<PostgreSQLConfig> {
    private static PostgreSQLConfig container;
    private static final String IMAGE_VERSION = "postgres:14.5";
    private static final String POSTGRES_USER = "user";
    private static final String POSTGRES_DB = "user";

    private PostgreSQLConfig() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLConfig getInstance() {
        if (container == null) {
            container = new PostgreSQLConfig();
        }
        container.withEnv("POSTGRES_USER", POSTGRES_USER);
        container.withEnv("POSTGRES_DB", POSTGRES_DB);
        container.withClasspathResourceMapping("/db/db-init.sql", "/docker-entrypoint-initdb.d/", BindMode.READ_ONLY);
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {}
}
