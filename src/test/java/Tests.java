import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {
    Register register;
    @Before
    public void setupRegister(){
        register = new Register();
    }

//  register
    @Test
    public void whenNewRegisterIsQueriedForTotalItReturns0(){
        setupRegister();
        assertEquals(0, register.getTotal());
    }
    @Test
    public void whenOneItemAddedToRegisterPriceListSizeIs1(){
        setupRegister();
        register.addNewItemToPriceList("banana",1, 0);
        assertEquals(1, register.getPriceListSize());
    }
    @Test
    public void whenAOneDollarItemScannedTotalReturns1(){
        setupRegister();
        register.addNewItemToPriceList("banana",1, 0);
        register.scanItem("banana");
        assertEquals(1, register.getTotal());
    }
    @Test
    public void whenItemWithWeightIsScannedTotalIsIncreasedByWeight(){
        setupRegister();
        register.addNewItemToPriceList("banana", 1, 0);
        register.scanItemByWeight("banana", 3);
        assertEquals(3, register.getTotal());
    }
    //test item with markdown
    @Test
    public void whenMarkedDownItemScannedTotalIncreasedByPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
    }
    //test weighted item with markdown
    @Test
    public void whenWeighedItemMarkedDownReflectsUnitPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.scanItemByWeight("banana", 1.5);
        assertEquals(2, register.getTotal(), 0.01);
    }




//  seller pricelist
    PriceList priceList;

    @Before
    public void setupPriceList(){
        priceList = new PriceList();
    }

    @Test
    public void whenNewPriceListIsQueriedSizeIs0(){
        setupPriceList();
        assertEquals(0, priceList.getNumberOfItems());
    }
    @Test
    public void whenOneItemIsAddedToPriceListSizeIs1(){
        setupPriceList();
        priceList.addItem("bread loaf", 0.99, 0);
        assertEquals(1, priceList.getNumberOfItems());
    }
    @Test
    public void whenGetItemIsCalledOnItemInPriceListItemIsReturned(){
        setupPriceList();
        priceList.addItem("bread loaf", 0.99, 0);
        Item temp = new Item("bread loaf", 0.99, 0);
        assertEquals(0.99, priceList.getItem("bread loaf").getPrice(), 0.01);
    }


//  Item object
    @Test
    public void initializeNewItemWithAPriceQueryForPriceReturnsPrice(){
        Item orange = new Item("orange", 1.35, 0);
        assertEquals(1.35, orange.getPrice(), 0.01);
    }
    @Test
    public void initializeNewItemWithMarkdown10ForMarkdownReturns10(){
        Item orange = new Item("orange", 1.35, 0.10);
        assertEquals(0.10, orange.getMarkdown(), 0.01);
    }
}
