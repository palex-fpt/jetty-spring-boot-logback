package me.palex.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootConfiguration
public class Application extends SpringBootServletInitializer {

    static {
        // preload of ch.qos.logback.core.spi.FilterReply allows to start servlet in jetty container with logback logging
        if (false) {
             System.out.println(ch.qos.logback.core.spi.FilterReply.class.toString());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> restServlet(ApplicationContext applicationContext) {
        AnnotationConfigWebApplicationContext restWebApplicationContext = new AnnotationConfigWebApplicationContext();
        restWebApplicationContext.setParent(applicationContext);
        restWebApplicationContext.register(RestConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setApplicationContext(restWebApplicationContext);
        dispatcherServlet.setContextConfigLocation(null);

        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(dispatcherServlet, "/rest/*");
        registration.setLoadOnStartup(1);
        registration.setName("rest");
        return registration;
    }
}