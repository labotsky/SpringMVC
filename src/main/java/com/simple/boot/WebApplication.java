package com.simple.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.simple.converter.DateConverter;

@SpringBootApplication
@ComponentScan("com.simple.bean, com.simple.service")
@EntityScan("com.simple.entity")
@EnableJpaRepositories("com.simple.repository")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class WebApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter("yyyy-MM-dd"));
    }

}
