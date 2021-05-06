package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.util.*;

/**
 * <summary>
 * Implement class according to description of task.
 * </summary>
 */

public class Client implements Iterable<Deposit>{
    private List<Deposit> deposits;

    private int capacity = 10;

    public Client() {
        this.deposits = new ArrayList<>(10);
    }

    public Client(List<Deposit> deposits){
        if(deposits == null){
            throw new IllegalArgumentException("Cannot create array of null deposits.");
        }
        this.deposits = deposits;
        this.capacity-=deposits.size();
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public boolean addDeposit(Deposit deposit){
        if(deposit == null){
            throw new IllegalArgumentException("Cannot add null deposit.");
        }
        if(this.capacity == 0){
            return false;
        }

        deposits.add(deposit);
        this.capacity--;

        return true;
    }

    public BigDecimal totalIncome(){
        return deposits.stream()
                .map(Deposit::income)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal maxIncome(){
        return deposits.stream()
                .map(Deposit::income)
                .max(BigDecimal::compareTo)
                .orElseThrow();
    }

    public BigDecimal getIncomeByNumber(int number){
        try {
            return deposits.get(number).income();
        }catch (IndexOutOfBoundsException e){
            return BigDecimal.ZERO;
        }
    }

    public void sortDeposits(){
        deposits.sort(Deposit::compareTo);
    }

    public int countPossibleToProlongDeposit(){
        return (int) deposits.stream()
                .filter(deposit -> deposit instanceof Prolongable && ((Prolongable) deposit).canToProlong())
                .count();
    }

    @Override
    public Iterator<Deposit> iterator() {
        return new Iterator<>() {
            private int currIndex = 0;

            @Override
            public boolean hasNext() {
                return currIndex < deposits.size();
            }

            @Override
            public Deposit next(){
                try {
                    if(currIndex == deposits.size()) {
                        throw new NoSuchElementException();
                    }

                    return deposits.get(currIndex++);
                }catch (IndexOutOfBoundsException e){
                    return null;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

