package nl.benooms.enverspringdataplayground;

import nl.benooms.enverspringdataplayground.configuration.ApplicationAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EnversSpringDataPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnversSpringDataPlaygroundApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new ApplicationAuditorAware();
	}

}
