package kz.ne.railways.tezcustoms.uaa;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@ComponentScan({ "kz.ne.railways.tezcustoms" })
public class TezCustomsUaaApp {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(TezCustomsUaaApp.class);
        springApplication.setAddCommandLineProperties(false);

        springApplication.setBannerMode(Banner.Mode.CONSOLE);

        ApplicationContext applicationContext = springApplication.run(args);
        applicationContext.getId();
    }
}