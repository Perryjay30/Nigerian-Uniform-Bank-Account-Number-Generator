package com.codingchallenge.nubangenerator.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateNubanRequest {
    private String institutionCode;
    private String serial;
}
