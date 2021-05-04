package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class SpecialDepositTest {

    @Test
    public void testIncome(){
        Deposit deposit = new SpecialDeposit(BigDecimal.valueOf(1000), 2);
        BigDecimal expected = new BigDecimal("30.20");

        Assert.assertEquals(deposit.income(), expected);
    }

    @Test
    public void testCanToProlong(){
        SpecialDeposit deposit = new SpecialDeposit(BigDecimal.valueOf(2000), 5);
        Assert.assertTrue(deposit.canToProlong());
    }

    @Test
    public void testCanToProlongFalse(){
        SpecialDeposit deposit = new SpecialDeposit(BigDecimal.valueOf(100), 5);
        Assert.assertFalse(deposit.canToProlong());
    }
}