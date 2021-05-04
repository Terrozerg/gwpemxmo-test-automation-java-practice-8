package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class DepositTest {
    private Deposit deposit;

    @BeforeClass
    public void setDeposit(){
        deposit = new BaseDeposit(BigDecimal.valueOf(1500), 5);
    }

    @Test
    public void testGetAmount(){
        Assert.assertEquals(deposit.getAmount(), BigDecimal.valueOf(1500));
    }

    @Test
    public void testGetPeriod(){
        Assert.assertEquals(deposit.getPeriod(), 5);
    }
}