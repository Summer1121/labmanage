package com.ncepu.feilong505.LabManage.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月27日
 */
@Configuration
public class HttpConfig {

    @Value("${server.http.port}")
    private int httpPort;

    @Bean
    public ServletWebServerFactory tomcatConfig() {
	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
	tomcat.addAdditionalTomcatConnectors(createStandardConnector());
	return tomcat;
    }

    private Connector createStandardConnector() {
	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	connector.setPort(httpPort);
	return connector;
    }
}
