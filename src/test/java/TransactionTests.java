import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionTests {
    PriceList priceList;
    Transaction transaction;
    @Before
    public void setupTransaction(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.addItem("banana", .5, 0);
        transaction = new Transaction(priceList);
    }
    @Test
    public void newTransactionTotalReturns0(){
        setupTransaction();
        assertEquals(0, transaction.getTotal(), 0.01);
    }
    @Test
    public void scan1DollarItemTotalIncreasesBy1(){
        setupTransaction();
        transaction.scanItem("bread loaf");
        assertEquals(1, transaction.getTotal(), 0.01);
    }
    @Test
    //price is 50c so 2x.5 = 1
    public void scanItemByWeightIncreasesTotalByWeightxPrice(){
        setupTransaction();
        transaction.scanItemByWeight("banana", 2);
        assertEquals(1, transaction.getTotal(), 0.1);
    }
    @Test
    public void twoTwoPoundBananasTotal2Dollars(){
        setupTransaction();
        transaction.scanItemByWeight("banana", 2);
        transaction.scanItemByWeight("banana", 2);
        assertEquals(2, transaction.getTotal(), 0.1);
    }
    @Test
    public void twoTwoPoundBananasAndBreadLoafReturn3(){
        setupTransaction();
        transaction.scanItem("bread loaf");
        transaction.scanItemByWeight("banana", 2);
        transaction.scanItemByWeight("banana", 2);
        assertEquals(3, transaction.getTotal(), 0.1);
    }
    @Test
    public void two1PoundBananasReturn1(){
        setupTransaction();
        transaction.scanItemByWeight("banana", 1);
        transaction.scanItemByWeight("banana", 1);
        assertEquals(1, transaction.getTotal(), 0.1);
    }
    @Test
    public void two1PoundBananasAndBreadLoafReturn2(){
        setupTransaction();
        transaction.scanItem("bread loaf");
        transaction.scanItemByWeight("banana", 1);
        transaction.scanItemByWeight("banana", 1);
        assertEquals(2, transaction.getTotal(), 0.1);
    }

    //applying specials
    //special should be applied to total as soon as the minimum requirements are met
    @Before
    public void setupSpecial(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.addItem("banana", 1, 0);
        priceList.setSpecial("bread loaf", 1,2,100);
        transaction = new Transaction(priceList);
    }

    @Test
    public void afterNItemIsScannedSpecialIsApplied(){
        setupSpecial();
        transaction.scanItem("bread loaf");
        transaction.scanItem("bread loaf");
        transaction.scanItem("bread loaf");
        assertEquals(1, transaction.getTotal(), 0.1);
    }







}
