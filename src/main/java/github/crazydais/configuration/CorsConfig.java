package github.crazydais.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class CorsConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/*")
            .allowedOrigins("*")
            .allowedMethods("POST, PUT, PATCH, GET, OPTIONS, DELETE")
            //.allowedHeaders("x-requested-with", "x-requested-from")
            //.exposedHeaders("header1", "header2")
            .allowCredentials(false).maxAge(3600);
  } 
  
  
}
