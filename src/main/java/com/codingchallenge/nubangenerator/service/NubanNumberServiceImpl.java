package com.codingchallenge.nubangenerator.service;

import com.codingchallenge.nubangenerator.data.model.Bank;
import com.codingchallenge.nubangenerator.data.model.NubanNumber;
import com.codingchallenge.nubangenerator.data.repository.NubanNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NubanNumberServiceImpl implements NubanNumberService {

    private final NubanNumberRepository nubanRepository;

    private final int serialNumLength = 9;

    private final List<Bank> banks = Arrays.asList(
            new Bank("ACCESS BANK", "044"),
            new Bank("CITIBANK", "023"),
            new Bank("DIAMOND BANK", "063"),
            new Bank("ECOBANK NIGERIA", "050"),
            new Bank("FIDELITY BANK", "070"),
            new Bank("FIRST BANK OF NIGERIA", "011"),
            new Bank("FIRST CITY MONUMENT BANK", "214"),
            new Bank("GUARANTY TRUST BANK", "058"),
            new Bank("HERITAGE BANK", "030"),
            new Bank("JAIZ BANK", "301"),
            new Bank("KEYSTONE BANK", "082"),
            new Bank("PROVIDUS BANK", "101"),
            new Bank("SKYE BANK", "076"),
            new Bank("STANBIC IBTC BANK", "221"),
            new Bank("STANDARD CHARTERED BANK", "068"),
            new Bank("STERLING BANK", "232"),
            new Bank("SUNTRUST", "100"),
            new Bank("UNION BANK OF NIGERIA", "032"),
            new Bank("UNITED BANK FOR AFRICA", "033"),
            new Bank("UNITY BANK", "215"),
            new Bank("WEMA BANK", "035"),
            new Bank("ZENITH BANK", "057")
    );

    @Override
    public String createAccountWithSerial(String bankCode, String serialNumber) {
        Bank bank = banks.stream()
                .filter(b -> b.getCode().equals(bankCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("We do not recognize" +
                        " this code as a Nigerian commercial bank code"));
        try {
            String paddedSerialNumber = String.format("%0" + serialNumLength + "d", Long.parseLong(serialNumber));
            String nuban = paddedSerialNumber + generateCheckDigit(paddedSerialNumber, bankCode);
            NubanNumber nubanNumber = new NubanNumber();
            nubanNumber.setSerialNumber(paddedSerialNumber);
            nubanNumber.setBankCode(bankCode);
            nubanNumber.setNuban(nuban);
            nubanRepository.save(nubanNumber);
            return nubanNumber.getNuban();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private  int generateCheckDigit(String serialNumber, String bankCode) {
        int [] seed = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};
        if (serialNumber.length() > serialNumLength) {
            throw new IllegalArgumentException("Serial number should be at most " + serialNumLength + "-digits long.");
        }

        serialNumber = String.format("%0" + serialNumLength + "d", Long.parseLong(serialNumber));
        String cipher = bankCode + serialNumber;
        int sum = 0;

        for (int i = 0; i < cipher.length(); i++) {
            int digit = Character.getNumericValue(cipher.charAt(i));
            sum += digit * seed[i];
        }

        int checkDigit = 10 - (sum % 10);
        if(checkDigit == 10) {
            checkDigit = 0;
        }
        return checkDigit;
    }
}
