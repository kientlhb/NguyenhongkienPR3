package k23cnt3.ngd.day02.pkg_annotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
    @Bean
    public String appName(){
        return "<h1>Nguyen Gia Duy</h1><h2>Spring Boot Application";
    }
}
