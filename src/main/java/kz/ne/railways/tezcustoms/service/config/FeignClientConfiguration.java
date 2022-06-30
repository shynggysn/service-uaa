package kz.ne.railways.tezcustoms.service.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "kz.ne.railways.tezcustoms.service.service")
public class FeignClientConfiguration {
}
