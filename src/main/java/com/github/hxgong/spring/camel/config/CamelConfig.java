package com.github.hxgong.spring.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hxgong.spring.camel.routes.SampleRoutes;

@Configuration
public class CamelConfig extends CamelConfiguration {

    @Bean
    public RouteBuilder route() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer://foo?period=5000&daemon=false").
                        setBody().constant("<hello>world!</hello>").
                        log(">>> ${body}");
            }
        };
    }
    
    @Bean
    public SampleRoutes route2() {
    	return new SampleRoutes();
    }

    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        // make Camel aware of Spring Boot’s application.properties
        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation("classpath:application.properties");
        camelContext.addComponent("properties", pc);

        // enable performance metrics
        camelContext.addRoutePolicyFactory(new MetricsRoutePolicyFactory());

        super.setupCamelContext(camelContext);
    }
}