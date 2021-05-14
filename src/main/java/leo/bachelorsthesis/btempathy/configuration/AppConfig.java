package leo.bachelorsthesis.btempathy.configuration;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"leo.bachelorsthesis.btempathy.configuration"})
public class AppConfig {

    @Value("${azureEndpoint}")
    String azureCognitiveServiceUrl;

    @Bean
    public AzureCognitiveServicesEmotionClient empathyClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(AzureCognitiveServicesEmotionClient.class, azureCognitiveServiceUrl);
    }

    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }
}
