package nl.huisartsPortal.microservices.keycloakauthenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KeycloakAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakAuthenticationServiceApplication.class, args);
	}
}
