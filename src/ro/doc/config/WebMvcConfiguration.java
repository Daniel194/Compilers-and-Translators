package ro.doc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ro.doc.services.TaskService;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "ro.doc")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public TaskService taskService() {
        return new TaskService();
    }
}
