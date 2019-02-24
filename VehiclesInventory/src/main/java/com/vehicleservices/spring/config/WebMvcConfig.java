package com.vehicleservices.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.vehicleservices.spring.exception.ErrorResponse;
import com.vehicleservices.spring.helper.ResponseHelper;
import com.vehicleservices.spring.logger.LoggingAspect;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.vehicleservices.spring" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	@Bean
	public LoggingAspect getControllerAspect() {
		return new LoggingAspect();
	}

	@Bean
	public ErrorResponse getErrorResponse() {
		return new ErrorResponse();
	}

	@Bean
	public ResponseHelper getResponseHelper() {
		return new ResponseHelper();
	}

}
