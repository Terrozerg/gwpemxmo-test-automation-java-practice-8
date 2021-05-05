package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class LongDepositTest {
    Deposit deposit;

    @Test
    public void testIncomeBeforeSixMonth(){
        deposit = new LongDeposit(BigDecimal.valueOf(1500), 5);

        Assert.assertEquals(deposit.income(), BigDecimal.ZERO);
    }

    @Test
    public void testIncomeAfterSixMonth(){
        deposit = new LongDeposit(BigDecimal.valueOf(2000), 8);
        BigDecimal expected = new BigDecimal("645.00");

        Assert.assertEquals(deposit.income(), expected);
    }

    @Test
    public void testCanToProlong(){
        LongDeposit deposit = new LongDeposit(BigDecimal.valueOf(100), 2);
        Assert.assertTrue(deposit.canToProlong());
    }

    @Test
    public void testCanToProlongFalse(){
        LongDeposit deposit = new LongDeposit(BigDecimal.valueOf(100), 36);
        Assert.assertFalse(deposit.canToProlong());
    }
}