package com.codingchallenge.nubangenerator.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountUtilTest {

    private BankAccountUtil bankAccountUtil;

    @BeforeEach
    void AlwaysStartsThis() {
        bankAccountUtil = new BankAccountUtil();
    }

    @Test
    public void testCreateAccountWithSerial_ValidBankCode() {
        String bankCode = "044";
        String serialNumber = "000000001";
        BankAccount account = bankAccountUtil.createAccountWithSerial(bankCode, serialNumber);

        Assertions.assertEquals(serialNumber, account.getSerialNumber());
//        Assertions.assertEquals("0178655911", account.getNuban());
        Assertions.assertEquals(bankCode, account.getBankCode());
        Assertions.assertEquals(new Bank("ACCESS BANK", "044"), account.getBank());
        System.out.println(account.getNuban());
    }

}