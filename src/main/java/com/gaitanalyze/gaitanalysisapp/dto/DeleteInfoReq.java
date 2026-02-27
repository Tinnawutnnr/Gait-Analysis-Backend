package com.gaitanalyze.gaitanalysisapp.dto;

import jakarta.validation.constraints.NotNull;

public class DeleteInfoReq {
    @NotNull(message = "Patient ID is required.")
    private Long UserId;
}
