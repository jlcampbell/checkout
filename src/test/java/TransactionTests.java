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
    //scan item by weight, no special
    //price is 50c so 2x.5 = 1
    public void scanItemByWeightIncreasesTotalByWeightxPrice(){
        setupTransaction();
        transaction.scanItemByWeight("banana", 2);
        assertEquals(1, transaction.getTotal(), 0.1);
    }
//    @Test
//    public void twoTwoPoundBananasTotal2Dollars(){
//        setupTransaction();
//        transaction.scanItemByWeight("banana", 2);
//        transaction.scanItemByWeight("banana", 2);
//        assertEquals(2, transaction.getTotal(), 0.1);
//    }
//    @Test
//    public void twoTwoPoundBananasAndBreadLoafReturn3(){
//        setupTransaction();
//        transaction.scanItem("bread loaf");
//        transaction.scanItemByWeight("banana", 2);
//        transaction.scanItemByWeight("banana", 2);
//        assertEquals(3, transaction.getTotal(), 0.1);
//    }
//    @Test
//    public void two1PoundBananasReturn1(){
//        setupTransaction();
//        transaction.scanItemByWeight("banana", 1);
//        transaction.scanItemByWeight("banana", 1);
//        assertEquals(1, transaction.getTotal(), 0.1);
//    }
//    @Test
//    public void two1PoundBananasAndBreadLoafReturn2(){
//        setupTransaction();
//        transaction.scanItem("bread loaf");
//        transaction.scanItemByWeight("banana", 1);
//        transaction.scanItemByWeight("banana", 1);
//        assertEquals(2, transaction.getTotal(), 0.1);
//    }

    //applying specials
    //special should be applied to total as soon as the minimum requirements are met
    @Before
    public void setupTransactionWithSomeSpecials(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.addItem("banana", .5, 0);
        priceList.setSpecialBuyNGetMForXPercentOff("banana", 1, 1, 50);
        transaction = new Transaction(priceList);
    }
    @Test
    public void testThatSpecialIsNotAppliedWhenOnlyOneItemOfEachTypeIsBought(){
        setupTransactionWithSomeSpecials();
        //p = .5
        transaction.scanItem("banana");
        //p = 1
        transaction.scanItem("bread loaf");
        double expected = 1.5;
        double actual = transaction.getTotal();
        assertEquals(expected, actual, 0.01);
    }
    @Test
    public void whenSpecialIsAppliedScanningTwoBananasShouldReturn75cents(){
        setupTransactionWithSomeSpecials();
        transaction.scanItem("banana");
        transaction.scanItem("banana");
        double expected = 0.75;
        double actual = transaction.getTotal();
        assertEquals(expected, actual, 0.01);
    }
    @Test
    public void whenTwoForOneSpecialAppliedTwoBreadsShouldReturn1(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.setSpecialXForY("bread loaf", 2, 1);
        transaction = new Transaction(priceList);
        transaction.scanItem("bread loaf");
        transaction.scanItem("bread loaf");
        double expected = 1;
        double actual = transaction.getTotal();
        assertEquals(expected, actual, 0.01);
    }
    @Test
    public void whenItemsScannedByWeightTotalReturnsWeightTimesPriceOfItemAndSpecialApplies(){
        priceList = new PriceList();
        priceList.addItem("pumpkin", 3, 0);
        priceList.setSpecialBuyNGetMForXPercentOff("pumpkin",1, 1, 50);
        transaction = new Transaction(priceList);
        transaction.scanItemByWeight("pumpkin", 1);
        transaction.scanItemByWeight("pumpkin", 1);
        double expected = 4.50;
        double actual = transaction.getTotal();
        assertEquals(expected, actual, 0.01);
    }
    //removeItem - generic item with no specials and no weight
    @Test
    public void totalAfterItemAddedAndRemovedReturns0(){
        priceList = new PriceList();
        priceList.addItem("candy", 1.5, 0);
        transaction = new Transaction(priceList);
        transaction.scanItem("candy");
        assertEquals(1.50, transaction.getTotal(), 0.01);
        transaction.removeItem("candy");
        assertEquals(0, transaction.getTotal(), 0.01);
    }









}
