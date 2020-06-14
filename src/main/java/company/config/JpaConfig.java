package company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(enableDefaultTransactions = false, basePackages = "company.domain.repository")
public class JpaConfig {

}
