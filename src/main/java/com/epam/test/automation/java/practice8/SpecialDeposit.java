package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

/**
 * <summary>
 * Implement class according to description of task.
 * </summary>
 */

public class SpecialDeposit extends Deposit implements Prolongable{

    public SpecialDeposit(BigDecimal amount, int period) {
        super(amount, period);
    }

    @Override
    public BigDecimal income() {
        BigDecimal amount = new BigDecimal(String.valueOf(this.getAmount()));
        int period = this.getPeriod();

        return super.incomeBase(amount, 0, period, 1, 1);
    }

    @Override
    public boolean canToProlong() {
        int result = this.getAmount().compareTo(BigDecimal.valueOf(1000));
        return result >= 0;
    }
}

