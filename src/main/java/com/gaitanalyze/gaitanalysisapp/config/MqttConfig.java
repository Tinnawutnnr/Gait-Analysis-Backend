package com.gaitanalyze.gaitanalysisapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt.broker")
public class MqttConfig {
    private String url;
    private String username;
    private String password;
}