package com.gaitanalyze.gaitanalysisapp.caretaker;

import jakarta.validation.Valid;

public interface CaretakerService {
    Caretaker getCaretakerById(Long caretakerId);

    void updateCaretaker(Long caretakerId, @Valid Caretaker caretakerReq);

    void deleteCaretaker(Long caretakerId);
}
