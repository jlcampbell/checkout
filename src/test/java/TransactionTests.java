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
        assertEquals(0, transaction.getTotal());
    }
    @Test
    public void scan1DollarItemTotalIncreasesBy1(){
        setupTransaction();
        transaction.scanItem("bread loaf");
        assertEquals(1, transaction.getTotal());
    }
    @Test
    //price is 50c so 2x.5 = 1
    public void scanItemByWeightIncreasesTotalByWeightxPrice(){
        setupTransaction();
        transaction.scanItemByWeight("banana", 2);
        assertEquals(1, transaction.getTotal(), 0.1);
    }






}
