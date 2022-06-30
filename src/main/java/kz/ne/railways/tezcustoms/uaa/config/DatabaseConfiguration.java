package kz.ne.railways.tezcustoms.uaa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan({ "kz.ne.railways.tezcustoms.common" })
@EnableJpaRepositories({"kz.ne.railways.tezcustoms.common"})
@EnableTransactionManagement
public class DatabaseConfiguration {
}
