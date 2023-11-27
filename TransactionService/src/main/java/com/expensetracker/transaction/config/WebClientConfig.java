package com.expensetracker.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    /**
     * Configures and provides a WebClient bean.
     *
     * @return Configured WebClient bean.
     */
    @Bean
    public WebClient expenseTrackerWebClient() {
        return WebClient.builder().build();
    }
}
