package com.myf.wchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:defineSys.properties")
@EnableJpaRepositories(basePackages = "com.myf.wchat.domain.repository")
public class WchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WchatApplication.class, args);
	}
}
