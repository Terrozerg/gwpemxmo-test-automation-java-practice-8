package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

/**
 * <summary>
 * Implement class according to description of task.
 * </summary>
 */

public class LongDeposit extends Deposit implements Prolongable{
    private static final int INCOME_PERCENT = 15;

    public LongDeposit(BigDecimal amount, int period) {
        super(amount, period);
    }

    @Override
    public BigDecimal income() {
        BigDecimal amount = new BigDecimal(String.valueOf(this.getAmount()));
        int period = this.getPeriod();
        if(period < 7){
            return BigDecimal.ZERO;
        }

        return super.incomeBase(amount, 6, period, INCOME_PERCENT, 0);
    }

    @Override
    public boolean canToProlong() {
        return this.getPeriod() <= 3;
    }
}

