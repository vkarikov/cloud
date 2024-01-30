package ru.vkarikov.cloud.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@Configuration
@Import({DataSourceAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
public class EnvironmentBootstrapConfiguration implements Ordered {

	private final JdbcTemplate jdbcTemplate;

    public EnvironmentBootstrapConfiguration(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
	public int getOrder() {
		return 0;
	}

	@PostConstruct
	private void init() {
		jdbcTemplate.execute("""
   		CREATE TABLE IF NOT EXISTS PROPERTIES (
        `KEY` VARCHAR(1000),
        `VALUE` VARCHAR(1000),
        APPLICATION VARCHAR(255),
        PROFILE VARCHAR(255),
        LABEL VARCHAR(255)
        )
		""");
	}

}
