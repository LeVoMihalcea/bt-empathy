package leo.bachelorsthesis.btempathy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@RefreshScope
@EnableConfigurationProperties
@SpringBootApplication
@EnableDiscoveryClient
public class BtEmpathyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtEmpathyApplication.class, args);
	}

}
