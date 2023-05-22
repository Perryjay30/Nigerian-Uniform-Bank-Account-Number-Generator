package com.codingchallenge.nubangenerator.service;

import com.codingchallenge.nubangenerator.data.dto.request.GenerateNubanRequest;
import com.codingchallenge.nubangenerator.data.model.NubanNumber;

public interface NubanNumberService {
    String createAccountWithSerial(String bankCode, String serialNumber);
}
