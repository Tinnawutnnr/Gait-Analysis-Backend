package com.gaitanalyze.gaitanalysisapp.mqtt;

import com.gaitanalyze.gaitanalysisapp.dto.MqttCredentialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mqtt")
@RequiredArgsConstructor
public class MqttAuthController {

    private final MqttAuthService mqttAuthService;

    @GetMapping("/credentials")
    public ResponseEntity<MqttCredentialResponse> getCredentials() {
        // check user auth
        return ResponseEntity.ok(mqttAuthService.generateCredentials());
    }
}