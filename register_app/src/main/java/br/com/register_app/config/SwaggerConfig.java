package br.com.register_app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
  private OpenAPI openAPI() {

    Contact contato = new Contact();
    contato.setName("Caio Ferraz");
    contato.setEmail("caioferrazalmeida.27@gmail.com");
    contato.setUrl("https://github.com/caiofrz");

    return new OpenAPI()
            .info(new Info()
                    .title("Register App")
                    .description("API para cadastro de usuarios")
                    .contact(contato)
            );
  }

}
