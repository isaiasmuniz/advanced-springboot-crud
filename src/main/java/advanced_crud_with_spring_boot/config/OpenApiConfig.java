package advanced_crud_with_spring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customAPI(){
        return new OpenAPI().info(new Info().title("Advanced crud with spring boot").version("v1")
                .description("Basic crud operations with pagination, documentation and tests implementations"));
    }
}
