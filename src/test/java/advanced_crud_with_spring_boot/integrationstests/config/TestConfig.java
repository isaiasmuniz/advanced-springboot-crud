package advanced_crud_with_spring_boot.integrationstests.config;

public interface TestConfig {
    int SERVER_PORT = 8888;

    String ORIGIN_LOCAL = "http://localhost:8080";
    String HEADER_PARAM_ORIGIN = "Origin";
}
