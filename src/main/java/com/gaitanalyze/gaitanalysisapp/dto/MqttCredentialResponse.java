package com.gaitanalyze.gaitanalysisapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MqttCredentialResponse {
    private String brokerUrl;
    private String username;
    private String password;
    private String clientIdPrefix;
}