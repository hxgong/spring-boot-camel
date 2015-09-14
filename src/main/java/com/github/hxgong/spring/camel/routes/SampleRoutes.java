package com.github.hxgong.spring.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SampleRoutes extends RouteBuilder {
    private static final transient Logger LOG = LoggerFactory.getLogger(SampleRoutes.class);

    @Value("${echo.value}") String echoStringValue;

    @Override
    public void configure() throws Exception {

        from("timer://foo?fixedRate=true&period=20000") //60 seconds
                .to("direct:audit");

        from("direct:audit")
                .setBody(constant("HELLO WORLD")).convertBodyTo(String.class)
                .to("direct:logme");

        from("direct:logme")
                .to("log:com.github.hxgong.spring.camel.routes.SampleRoutes");
    }

    public String getEchoStringValue() {
        return echoStringValue;
    }
}
