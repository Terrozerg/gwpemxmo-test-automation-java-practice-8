package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientTest {
    private List<Deposit> deposits = new ArrayList<>(List.of(
            new BaseDeposit(BigDecimal.valueOf(1000), 7),
            new BaseDeposit(BigDecimal.valueOf(2000), 3),
            new LongDeposit(BigDecimal.valueOf(1000), 37),
            new SpecialDeposit(BigDecimal.valueOf(5000), 5),
            new SpecialDeposit(BigDecimal.valueOf(1300), 7)
    ));
    Client client = new Client(deposits);

    @Test(expectedExceptions = IllegalArgumentException.class,
    expectedExceptionsMessageRegExp = "Cannot create array of null deposits.")
    public void testConstructorWithNullDeposits(){
        Client nullClients = new Client(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
    expectedExceptionsMessageRegExp = "Cannot add null deposit.")
    public void testAddNullDeposit(){
        Client addClient = new Client();

        addClient.addDeposit(null);
    }

    @Test
    public void testAddDeposit(){
        Client addClient = new Client();

        Assert.assertTrue(addClient.addDeposit(deposits.get(1)));
    }

    @Test
    public void testAddDepositToFullClient(){
        Client addClient = new Client();
        for (int i = 0; i < 10; i++) {
            addClient.addDeposit(new BaseDeposit(BigDecimal.valueOf(1500), 8));
        }

        Assert.assertFalse(addClient.addDeposit(deposits.get(1)));
    }

    @Test
    public void testGetIncomeByNumber(){
        Assert.assertEquals(client.getIncomeByNumber(4), deposits.get(4).income());
    }

    @Test
    public void testGetIncomeByOutOfBoundsNumber(){
        Assert.assertEquals(client.getIncomeByNumber(-1), BigDecimal.ZERO);
    }

    @Test
    public void testMaxIncome(){
        Assert.assertEquals(client.maxIncome(), deposits.get(2).income());
    }

    @Test
    public void testTotalIncome(){
        BigDecimal total = BigDecimal.ZERO;

        for (Deposit deposit : deposits) {
            total = total.add(deposit.income());
        }

        Assert.assertEquals(client.totalIncome(), total);
    }

    @Test
    public void testIteratorNext(){
        Iterator<Deposit> iterator = client.iterator();
        Assert.assertNotNull(iterator.next());
    }

    @Test
    public void testIteratorHasNext(){
        Iterator<Deposit> iterator = client.iterator();

        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextOnNoElements(){
        Client emptyClient = new Client();
        Iterator<Deposit> iterator = emptyClient.iterator();

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNoNext(){
        Iterator<Deposit> iterator = client.iterator();

        while (iterator.hasNext()){
            Assert.assertNotNull(iterator.next());
        }

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextInIteratorWhenHasNextElements(){
        Iterator<Deposit> iterator = client.iterator();

        while (iterator.hasNext()){
            Assert.assertNotNull(iterator.next());
        }

        iterator.next();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testIteratorNoNextElement(){
        List<Deposit> depositList = new ArrayList<>(List.of(
                new BaseDeposit(BigDecimal.valueOf(1000), 7),
                new BaseDeposit(BigDecimal.valueOf(2000), 3),
                new SpecialDeposit(BigDecimal.valueOf(5000), 5),
                new SpecialDeposit(BigDecimal.valueOf(1300), 7)
        ));
        depositList.add(null);
        depositList.add(null);

        Client test = new Client(depositList);

        Iterator<Deposit> iterator = test.iterator();

        while(iterator.hasNext()) {
            iterator.next();
        }

        iterator.next();
        iterator.next();
    }

    @Test
    public void testSortDeposits(){
        List<Deposit> sortedDeposits = new ArrayList<>(List.copyOf(deposits));
        sortedDeposits.sort(Deposit::compareTo);

        Client sortedClient = new Client(new ArrayList<>(List.copyOf(deposits)));
        sortedClient.sortDeposits();

        Assert.assertEquals(sortedDeposits, sortedClient.getDeposits());
    }

    @Test
    public void testSortDepositsWithEmptyDeposits(){
        List<Deposit> sortedDeposits = new ArrayList<>(0);
        sortedDeposits.sort(Deposit::compareTo);

        Client sortedClient = new Client(new ArrayList<>(0));
        sortedClient.sortDeposits();

        Assert.assertEquals(sortedDeposits, sortedClient.getDeposits());
    }

    @Test
    public void testCountPossibleToProlongDeposit(){
        Assert.assertEquals(client.countPossibleToProlongDeposit(), 2);
    }
}
