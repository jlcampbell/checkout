import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class Tests {
    Register register;
    @Before
    public void setupRegister(){
        register = new Register();
        register.startTransaction();
    }

//  register

//  register should be able to start a new transaction



//  register/transaction


    @Test
    public void whenNewRegisterIsQueriedForTotalItReturns0(){
        setupRegister();
        assertEquals(0, register.getTotal(), 0.01);
    }
    @Test
    public void whenAOneDollarItemScannedTotalReturns1(){
        setupRegister();
        register.addNewItemToPriceList("banana",1, 0);
        register.startTransaction();
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
    }
    @Test
    public void whenItemWithWeightIsScannedTotalIsIncreasedByWeight(){
        setupRegister();
        register.addNewItemToPriceList("banana", 1, 0);
        register.startTransaction();
        register.scanItemByWeight("banana", 3.0);
        assertEquals(3, register.getTotal(), 0.01);
    }
    //Req #3 test item with markdown
    @Test
    public void whenMarkedDownItemScannedTotalIncreasedByPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.startTransaction();
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
    }
    //Req #3 test weighted item with markdown
    @Test
    public void whenWeighedItemMarkedDownReflectsUnitPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.startTransaction();
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
        register.startTransaction();
        register.scanItem("apple");
        register.scanItem("apple");
        register.scanItem("apple");
        assertEquals(3, register.getTotalByName("apple"));
    }
    //Req #4 buy N items get M at x% off
    //Need to be able to add a special from the register

    //Req #4 buy N items get M at x% off
    //Special should be applied when 3 items are bought

    //Req #4 buy N items get M at x% off
    //Special should not be applied when too few items bought

    //Req #4 buy N items get M at x% off
    //Special should be applied also when 6 items are bought


    // register/pricelist
    @Test
    public void whenOneItemAddedToRegisterPriceListSizeIs1(){
        setupRegister();
        register.addNewItemToPriceList("banana",1, 0);
        assertEquals(1, register.getPriceListSize());
    }



//  pricelist
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
    //Req #4 buy N items get M at x% off
    //Need to be able to set a special in the pricelist and then retrieve it
    @Test
    public void addSpecialToItemGetSpecialsReturnsThatSpecial(){
        setupPriceList();
        priceList.addItem("bread loaf", 0.99, 0);
        priceList.setSpecial("bread loaf", 3, 1, 100);
        int actual1 = priceList.getSpecial("bread loaf").get("N");
        int actual2 = priceList.getSpecial("bread loaf").get("M");
        int actual3 = priceList.getSpecial("bread loaf").get("percent off");
        assertEquals(3, actual1, 0.1);
        assertEquals(1, actual2, 0.1);
        assertEquals(100, actual3, 0.1);
    }
    //Req #5 X for Y dollars
    //Need to be able to set a special in the pricelist and then retrieve it
    @Test
    public void addXForYSpecialToPriceListAndReturnIt(){
        setupPriceList();
        priceList.addItem("bread loaf", 0.99, 0);
        priceList.setXForYSpecial("bread loaf", 3, 5);
        int actual1 = priceList.getXForYSpecial("bread loaf").get("X");
        int actual2 = priceList.getXForYSpecial("bread loaf").get("YDollars");
        assertEquals(3, actual1, 0.1);
        assertEquals(5, actual2, 0.1);
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
    public void addSpecialToPriceListItemGetSpecialsReturnsThatSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setSpecial(3, 1, 100);
        assertEquals(3, orange.getSpecial().get("N"), 0.01);
        assertEquals(1, orange.getSpecial().get("M"), 0.01);
        assertEquals(100, orange.getSpecial().get("percent off"), 0.01);
    }
    //Req #5 support special X for $Y
    @Test
    public void addXForYdollarsSpecialReturnSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setXForYDollarsSpecial(5, 5);
        assertEquals(5, orange.getXForYSpecial().get("X"), 0.1);
        assertEquals(5, orange.getXForYSpecial().get("YDollars"), 0.1);
    }

    @Test
    public void addBuyKGetJOfEqualOrLesserValueForXpercentOffReturnSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setBuyKGetJEqualOrLessXOffSpecial(5, 3, 50);
        assertEquals(5, orange.getBuyKGetJEqualOrLessXOffSpecial().get("K"), 0.1);
        assertEquals(3, orange.getBuyKGetJEqualOrLessXOffSpecial().get("J"), 0.1);
        assertEquals(50, orange.getBuyKGetJEqualOrLessXOffSpecial().get("percentOffLesserValue"), 0.1);


    }


}
