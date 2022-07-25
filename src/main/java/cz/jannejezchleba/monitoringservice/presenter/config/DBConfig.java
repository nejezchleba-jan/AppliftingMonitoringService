package cz.jannejezchleba.monitoringservice.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"cz.jannejezchleba.monitoringservice.data.entities"})
@EnableJpaRepositories(basePackages = {"cz.jannejezchleba.monitoringservice.data.repositories"})
public class DBConfig {
}

