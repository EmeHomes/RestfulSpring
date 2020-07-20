package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		/*SessionLocaleResolver localeResolver = new SessionLocaleResolver(); era lo que teníamos antes. Ahora lo
		cambiamos por lo que viene a continuación porque despues de todo estamos cambiando el idioma en el header*/
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		Locale spanish = new Locale("es");
		localeResolver.setDefaultLocale(spanish);
		return localeResolver;
	}

	/*
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	Esto puede sustituirse por: spring.messages.basename=messages en el application properties*/
}
