package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

/**
 * <summary>
 * Implement class according to description of task.
 * </summary>
 */

public class BaseDeposit extends Deposit {
    private static final int INCOME_PERCENT = 5;

    public BaseDeposit(BigDecimal amount, int period) {
        super(amount, period);
    }

    @Override
    public BigDecimal income() {
        BigDecimal amount = new BigDecimal(String.valueOf(this.getAmount()));
        int period = this.getPeriod();

        return super.incomeBase(amount, 0, period, INCOME_PERCENT, 0);
    }
}

