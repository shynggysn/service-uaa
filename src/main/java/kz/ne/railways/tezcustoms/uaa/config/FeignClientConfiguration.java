package kz.ne.railways.tezcustoms.uaa.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "kz.ne.railways.tezcustoms.common.service")
public class FeignClientConfiguration {
}
