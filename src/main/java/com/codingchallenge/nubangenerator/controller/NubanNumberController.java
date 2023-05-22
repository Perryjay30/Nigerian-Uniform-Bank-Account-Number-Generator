package com.codingchallenge.nubangenerator.controller;

import com.codingchallenge.nubangenerator.data.dto.request.GenerateNubanRequest;
import com.codingchallenge.nubangenerator.data.model.NubanNumber;
import com.codingchallenge.nubangenerator.service.NubanNumberService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
@RequestMapping("/api/v1/generateNubanNumber")
public class NubanNumberController {

    private final NubanNumberService numberService;


    public NubanNumberController(NubanNumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping
    public ResponseEntity<String> generateNumber(@RequestBody GenerateNubanRequest generateNubanRequest) {
        String newNubanNumber = numberService.createAccountWithSerial(generateNubanRequest.getBankCode(), generateNubanRequest.getSerialNumber());
        return new ResponseEntity<>(newNubanNumber, HttpStatus.CREATED);
    }

       @Data
       public static class GenerateNubanRequest  {
         private String bankCode;
         private String serialNumber;
    }
}
