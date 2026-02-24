package com.gaitanalyze.gaitanalysisapp.gait.api;

import com.gaitanalyze.gaitanalysisapp.gait.application.GetPatientSessionsUseCase;
import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class GaitSessionController {

    private final GetPatientSessionsUseCase useCase;

    public GaitSessionController(GetPatientSessionsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{patientId}/gait-sessions")
    public List<GaitSession> getSession(@PathVariable Long patientId) {
        return useCase.execute(patientId);
    }
}