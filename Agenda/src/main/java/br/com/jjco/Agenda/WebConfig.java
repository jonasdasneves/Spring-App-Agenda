package br.com.jjco.Agenda;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite acesso de qualquer origem (caso precise restringir, mude o valor de "*")
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Permite apenas requisições de localhost:4200 (Angular)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("Content-Type", "Authorization") // Cabeçalhos permitidos
                .allowCredentials(true); // Se precisar permitir cookies/autenticação
    }
}
