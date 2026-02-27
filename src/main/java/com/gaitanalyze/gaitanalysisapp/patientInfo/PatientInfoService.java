package com.gaitanalyze.gaitanalysisapp.patientInfo;

import com.gaitanalyze.gaitanalysisapp.dto.DeleteInfoReq;
import com.gaitanalyze.gaitanalysisapp.dto.PatientInfoRequest;
import jakarta.validation.Valid;

public interface PatientInfoService {
    void createInfo(@Valid PatientInfoRequest request, Long caretakerId);

    void updateInfo(@Valid PatientInfoRequest request, Long caretakerId);
}
