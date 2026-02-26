package com.gaitanalyze.gaitanalysisapp.patientInfo;

import com.gaitanalyze.gaitanalysisapp.dto.PatientInfoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patientInfo")
public class PatientInfoController {

    private  PatientInfoService patientInfoService;

    public PatientInfoController(PatientInfoService patientInfoService) {
        this.patientInfoService = patientInfoService;
    }

    @PostMapping("/{caretakerId}")
    public ResponseEntity<String> createInfo(@Valid @RequestBody PatientInfoRequest request, @PathVariable Long caretakerId){
        patientInfoService.createInfo(request, caretakerId);
        return new ResponseEntity<>("Info has been created.", HttpStatus.CREATED);
    }
}
