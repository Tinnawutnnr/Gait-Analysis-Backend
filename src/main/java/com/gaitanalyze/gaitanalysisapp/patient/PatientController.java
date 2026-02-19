package com.gaitanalyze.gaitanalysisapp.patient;

import com.gaitanalyze.gaitanalysisapp.dto.PatientListDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/{caretakerId}")
    public ResponseEntity<List<PatientListDTO>> getPatients(@PathVariable Long caretakerId){
        List<PatientListDTO> usersFetches = patientService.getPatients(caretakerId);
        return new ResponseEntity<>(usersFetches, HttpStatus.OK);
    }

    @PostMapping("/patient/{caretakerId}")
    public ResponseEntity<String> createPatient(@Valid @RequestBody Patient patientReq, @PathVariable Long caretakerId){
        patientService.createPatient(patientReq, caretakerId);
        return new ResponseEntity<>("Patient created successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/patient/{caretakerId}/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long caretakerId, @PathVariable Long patientId){
        Patient patient = patientService.getPatientById(caretakerId, patientId);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/patient/{caretakerId}/{patientId}")
    public ResponseEntity<String> updatePatient(@PathVariable Long caretakerId, @PathVariable Long patientId, @Valid @RequestBody Patient patientReq){
        patientService.updatePatient(caretakerId, patientId, patientReq);
        return new ResponseEntity<>("Updated patient successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/patient/{caretakerId}/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long caretakerId, @PathVariable Long patientId){
        patientService.deletePatient(caretakerId, patientId);
        return new ResponseEntity<>("Deleted patient successfully.", HttpStatus.OK);
    }
}
