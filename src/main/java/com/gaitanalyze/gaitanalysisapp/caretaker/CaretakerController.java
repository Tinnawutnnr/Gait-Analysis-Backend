package com.gaitanalyze.gaitanalysisapp.caretaker;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CaretakerController {

    private CaretakerService caretakerService;

    public CaretakerController(CaretakerService caretakerService) {
        this.caretakerService = caretakerService;
    }

    @PostMapping("/caretaker")
    public ResponseEntity<String> createCaretaker(@Valid @RequestBody Caretaker caretakerReq){
        caretakerService.createCaretaker(caretakerReq);
        return new ResponseEntity<>("Caretaker created successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/caretaker/{caretakerId}")
    public ResponseEntity<Caretaker> getCaretakerById(@PathVariable Long caretakerId){
        Caretaker caretaker = caretakerService.getCaretakerById(caretakerId);
        return ResponseEntity.ok(caretaker);
    }

    @PutMapping("/caretaker/{caretakerId}")
    public ResponseEntity<String> updateCaretaker(@PathVariable Long caretakerId, @Valid @RequestBody Caretaker caretakerReq){
        caretakerService.updateCaretaker(caretakerId, caretakerReq);
        return new ResponseEntity<>("Updated caretaker successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/caretaker/{caretakerId}")
    public ResponseEntity<String> deleteCaretaker(@PathVariable Long caretakerId){
        caretakerService.deleteCaretaker(caretakerId);
        return new ResponseEntity<>("Deleted caretaker successfully.", HttpStatus.OK);
    }

}
