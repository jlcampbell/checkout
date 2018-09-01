import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {
//  register
    @Test
    public void whenNewRegisterIsQueriedForTotalItReturns0(){
        Register register = new Register();
        assertEquals(0, register.getTotal());
    }
    @Test
    public void whenOneItemAddedToRegisterPriceListSizeIs1(){
        Register register = new Register();
        register.addNewItemToPriceList("banana",1);
        assertEquals(1, register.getPriceListSize());
    }
    @Test
    public void whenAOneDollarItemScannedTotalReturns1(){
        Register register = new Register();
        register.addNewItemToPriceList("banana",1);
        register.scanItem("banana");
        assertEquals(1, register.getTotal());
    }
    @Test
    public void whenItemWithWeightIsScannedTotalIsIncreasedByWeight(){
        Register register = new Register();
        register.addNewItemToPriceList("banana", 1);
        register.scanItemByWeight("banana", 3);
        assertEquals(3, register.getTotal());
    }

//  seller pricelist
    @Test
    public void whenNewPriceListIsQueriedSizeIs0(){
        PriceList priceList = new PriceList();
        assertEquals(0, priceList.getNumberOfItems());
    }
    @Test
    public void whenOneItemIsAddedToPriceListSizeIs1(){
        PriceList priceList = new PriceList();
        priceList.addItem("bread loaf", 0.99);
        assertEquals(1, priceList.getNumberOfItems());
    }
    @Test
    public void whenGetItemIsCalledOnItemInPriceListItemIsReturned(){
        PriceList priceList = new PriceList();
        priceList.addItem("bread loaf", 0.99);
        Item temp = new Item("bread loaf", 0.99);
        assertEquals(0.99, priceList.getItem("bread loaf").getPrice(), 0.01);
    }


//  Item object
    @Test
    public void initializeNewItemWithAPriceQueryForPriceReturnsPrice(){
        Item orange = new Item("orange", 1.35);
        assertEquals(1.35, orange.getPrice(), 0.01);
    }
}
