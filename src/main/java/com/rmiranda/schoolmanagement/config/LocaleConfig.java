package com.rmiranda.schoolmanagement.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

   @Bean
   public MessageSource messageSource() {
       final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
       messageSource.setBasename("classpath:messages");
       messageSource.setDefaultEncoding("UTF-8");
       return messageSource;
   }

   @Bean
   public LocalValidatorFactoryBean validator(final MessageSource messageSource) {
       final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
       bean.setValidationMessageSource(messageSource);
       return bean;
   }

   @Bean
   public SessionLocaleResolver localeResolver() {
       final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
       localeResolver.setDefaultLocale(Locale.getDefault());
       return localeResolver;
   }

   @Bean
   public LocaleChangeInterceptor localeChangeInterceptor() {
       final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
       localeChangeInterceptor.setParamName("lang");
       return localeChangeInterceptor;
   }

   @Override
   public void addInterceptors(final InterceptorRegistry registry) {
       registry.addInterceptor(localeChangeInterceptor());
   }

}