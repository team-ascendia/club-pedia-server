package clubpedia.in.net.clubpedia.global.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "ClubPedia API 명세서",
                description = "ClubPedia 서비스의 API 문서입니다.",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "김가영",
                        email = "offbeat1020@naver.com",
                        url = "https://clubpedia.in.net"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement)
                .addServersItem(new Server().url("/"));
    }
}
