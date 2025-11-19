package advanced_crud_with_spring_boot.integrationstests.testecontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractionIntegrationTest.Initializer.class)
public class AbstractionIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
        static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:9.1.0");

        private static void startContainers(){
            Startables.deepStart(Stream.of(mySQLContainer)).join();
        }

        private static Map<String, String> createConnectionConfiguration(){
            return Map.of(
                    "spring.datasource.url", mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username", mySQLContainer.getUsername(),
                    "spring.datasource.password", mySQLContainer.getPassword(),
                    "server.port", "8888"
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environmen = applicationContext.getEnvironment();
            MapPropertySource testecontainers =  new MapPropertySource("testecontainers", (Map) createConnectionConfiguration());
            environmen.getPropertySources().addFirst(testecontainers);
        }
    }
}
