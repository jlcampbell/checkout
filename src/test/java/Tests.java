import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
    //Req #3 test item with markdown
    @Test
    public void whenMarkedDownItemScannedTotalIncreasedByPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
    }
    //Req #3 test weighted item with markdown
    @Test
    public void whenWeighedItemMarkedDownReflectsUnitPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.scanItemByWeight("banana", 1.5);
        assertEquals(2, register.getTotal(), 0.01);
    }
    //Req #4 buy N items get M at x% off
    //Assuming this means N and M items are all multiples of the same item
    //Need to keep track of the number of each item added
    @Test
    public void whenAppleAddedThreeTimesAppleTotalIs3(){
        setupRegister();
        register.addNewItemToPriceList("apple", 0.50, 0);
        register.scanItem("apple");
        register.scanItem("apple");
        register.scanItem("apple");
        assertEquals(3, register.getTotalByName("apple"));
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
    //Req #4 buy N items get M at x% off
    //need to be able to add specials to the item's information set in the pricelist
    @Test
    public void addSpecialToItemGetSpecialsReturnsThatSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setSpecial(3, 1, 100);
        HashMap<String, Integer> special = orange.getSpecial();
        assertEquals(3, orange.getSpecial().get("N"), 0.01);
        assertEquals(1, orange.getSpecial().get("M"), 0.01);
        assertEquals(100, orange.getSpecial().get("percent off"), 0.01);
    }

}
