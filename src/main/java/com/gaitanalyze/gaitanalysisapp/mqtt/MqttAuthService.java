package com.gaitanalyze.gaitanalysisapp.mqtt;

import com.gaitanalyze.gaitanalysisapp.config.MqttConfig;
import com.gaitanalyze.gaitanalysisapp.dto.MqttCredentialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MqttAuthService {

    private final MqttConfig mqttConfig;

    public MqttCredentialResponse generateCredentials() {
        return MqttCredentialResponse.builder()
                .brokerUrl(mqttConfig.getUrl())
                .username(mqttConfig.getUsername())
                .password(mqttConfig.getPassword())
                // Create prefix for mobile app
                .clientIdPrefix("mobile_" + UUID.randomUUID().toString().substring(0, 8) + "_")
                .build();
    }
}