package com.club.padel;

import java.util.Collections;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ClubPadelConfig implements WebMvcConfigurer{

	@Bean
	  public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	    localeResolver.setDefaultLocale(new Locale("es"));
	    return localeResolver;
	  }
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.club.padel.controller")) //$NON-NLS-1$
				.paths(PathSelectors.any())
				.build().apiInfo(getApiInfo())
				;
	}
	
	private springfox.documentation.service.ApiInfo getApiInfo() {
		return new ApiInfo(
				"Club Padel API", //$NON-NLS-1$
				"API to manage padel club reservations", //$NON-NLS-1$
				"1.0", //$NON-NLS-1$
				"http://club.padel/terms", //$NON-NLS-1$
				new Contact("ClubPadel", "https://clubpadel.com", "apis@clubpadel.com"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				"LICENSE", //$NON-NLS-1$
				"LICENSE URL", //$NON-NLS-1$
				Collections.emptyList()
				);
	}
	
    @Bean
    public ResourceBundleMessageSource messageSource() {

        var source = new ResourceBundleMessageSource();
        source.setBasenames("messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }	
}
