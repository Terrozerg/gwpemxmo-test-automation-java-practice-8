package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <summary>
 * Implement class according to description of task.
 * </summary>
 */

public abstract class Deposit implements Comparable<Deposit>{
    public final BigDecimal amount;
    public final int period;

    public Deposit(BigDecimal amount, int period){
        this.amount = amount;
        this.period = period;
    }

    public abstract BigDecimal income();

    public BigDecimal incomeBase(BigDecimal amount, int periodStart, int periodEnd, int percent, int increment){
        BigDecimal result = amount;
        BigDecimal addition;

        for (int i = periodStart; i < periodEnd; i++) {
            addition = result
                    .multiply(BigDecimal.valueOf(percent))
                    .divide(BigDecimal.valueOf(100), MathContext.DECIMAL64);

            result = result.add(addition);

            percent+=increment;
        }

        return result.subtract(amount).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public int compareTo(Deposit o) {
        return o.income().compareTo(this.income());
    }
}

