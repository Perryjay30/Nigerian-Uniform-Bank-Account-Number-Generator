package com.codingchallenge.nubangenerator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NubanNumberServiceImplTest {

    @Autowired
    NubanNumberService nubanNumberService;


    @Test
    void testThatNewNubanNumberCanBeGenerated() {
        String institutionCode = "044";
        String serial = "000000001";
        String newNubanNumber = nubanNumberService.createAccountWithSerial(institutionCode, serial);
        assertEquals("0000000017", newNubanNumber);
    }

    @Test
    void testThatNewNubanNumberCanBeGeneratedThrowsAndException() {
        String institutionCode = "089";
        String serial = "000000001";
        assertThrows(RuntimeException.class, () -> nubanNumberService.
                createAccountWithSerial(institutionCode, serial));
    }

//    @Test
//    public void testGenerateCheckDigit_ValidSerialNumber() {
//        String serialNumber = "000000001";
//        String bankCode = "044";
//        int checkDigit = BankAccountUtil.generateCheckDigit(serialNumber, bankCode);
//        Assertions.assertEquals(7, checkDigit);
//    }
//
//    @Test
//    public void testGenerateCheckDigit_InvalidSerialNumber() {
//        String serialNumber = "000000001111"; // Invalid serial number length
//        String bankCode = "044";
//        Assertions.assertThrows(IllegalArgumentException.class,
//                () -> BankAccountUtil.generateCheckDigit(serialNumber, bankCode));
//    }
//
//    @Test
//    public void testIsBankAccountValid_ValidAccountNumber() {
//        String accountNumber = "0000000017";
//        String bankCode = "044";
//        boolean isValid = BankAccountUtil.isBankAccountValid(accountNumber, bankCode);
//        Assertions.assertTrue(isValid);
//    }
//
//    @Test
//    public void testIsBankAccountValid_InvalidAccountNumber() {
//        String accountNumber = "0000000011"; // Invalid check digit
//        String bankCode = "044";
//        boolean isValid = BankAccountUtil.isBankAccountValid(accountNumber, bankCode);
//        Assertions.assertFalse(isValid);
//    }
}