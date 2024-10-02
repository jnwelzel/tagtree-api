package online.jonwelzel.tagtreeapi;

import online.jonwelzel.tagtreeapi.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class TagtreeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TagtreeApiApplication.class, args);
    }

}
