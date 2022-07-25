package ai.ecma.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class Auditing {

    @Bean
    public AuditorAware auditorAware() {
        return new AuditingImpl();
    }
}
