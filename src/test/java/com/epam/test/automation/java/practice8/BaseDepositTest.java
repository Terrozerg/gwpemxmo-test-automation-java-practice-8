package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class BaseDepositTest {

    @Test
    public void testIncome(){
        Deposit deposit = new BaseDeposit(BigDecimal.valueOf(3000), 6);

        Assert.assertEquals(deposit.income(), BigDecimal.valueOf(1020.29));
    }
}