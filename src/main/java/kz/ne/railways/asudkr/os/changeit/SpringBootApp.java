package kz.ne.railways.asudkr.os.changeit;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableJpaRepositories({"kz.ne.railways.asudkr.os"})
public class SpringBootApp {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(SpringBootApp.class);
        springApplication.setAddCommandLineProperties(false);

        springApplication.setBannerMode(Banner.Mode.CONSOLE);

        ApplicationContext applicationContext = springApplication.run(args);
        applicationContext.getId();
    }
}
