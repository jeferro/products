package com.jeferro.shared.infrastructure.adapters.rest.configurations;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocaleRestConfig {

  @Bean
  public LocaleResolver localeResolver() {
	AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
	resolver.setDefaultLocale(Locale.ROOT);

	return resolver;
  }
}
