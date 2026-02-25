package com.gaitanalyze.gaitanalysisapp.caretaker.impl;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerRepository;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerService;
import com.gaitanalyze.gaitanalysisapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CaretakerServiceImpl implements CaretakerService {

    private CaretakerRepository caretakerRepository;

    public CaretakerServiceImpl(CaretakerRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    @Override
    public Caretaker getCaretakerById(Long caretakerId) {
        return caretakerRepository.findById(caretakerId).
                orElseThrow(()-> new ResourceNotFoundException("Caretaker id with id: " + caretakerId + "not found"));

    }

    @Override
    public void updateCaretaker(Long caretakerId, Caretaker caretakerReq) {
        Caretaker caretakerFound = caretakerRepository.findById(caretakerId).
                orElseThrow(()->new ResourceNotFoundException("Caretaker id with id: " + caretakerId + "not found"));

        caretakerFound.setEmail(caretakerReq.getEmail());
        caretakerFound.setFullname(caretakerReq.getFullname());
        caretakerFound.setPassword(caretakerReq.getPassword());
        caretakerRepository.save(caretakerFound);
    }

    @Override
    public void deleteCaretaker(Long caretakerId) {
        Caretaker caretakerFound = caretakerRepository.findById(caretakerId).
                orElseThrow(()->new ResourceNotFoundException("Caretaker id with id: " + caretakerId + "not found"));

        caretakerRepository.delete(caretakerFound);
    }


}
