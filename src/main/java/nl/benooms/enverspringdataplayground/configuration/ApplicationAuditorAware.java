package nl.benooms.enverspringdataplayground.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Configuration class for enabling Spring Data auditing
 * @author Ben Ooms
 */
public class ApplicationAuditorAware implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Gebruiker");
	}
}
