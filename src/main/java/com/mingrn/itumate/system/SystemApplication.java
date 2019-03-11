package com.mingrn.itumate.system;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.mingrn.itumate")
@EnableFeignClients(basePackages = "com.mingrn.itumate")
public class SystemApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SystemApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}