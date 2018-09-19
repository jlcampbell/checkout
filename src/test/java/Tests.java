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

////  register
//
////  register should be able to start a new transaction

////  register/transaction
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
//    //Req #3 test item with markdown
    @Test
    public void whenMarkedDownItemScannedTotalIncreasedByPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.startTransaction();
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
    }
//    //Req #3 test weighted item with markdown
    @Test
    public void whenWeighedItemMarkedDownReflectsUnitPriceMinusMarkdown(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.startTransaction();
        register.scanItemByWeight("banana", 1.5);
        assertEquals(2, register.getTotal(), 0.01);
    }
    //remove an item
    @Test
    public void whenItemAddedAndThenRemovedTotalIsZero(){
        setupRegister();
        register.addNewItemToPriceList("banana", 2, 1);
        register.startTransaction();
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
        register.removeItem("banana");
        assertEquals(0, register.getTotal(), 0.01);
    }

//    //Req #4 buy N items get M at x% off
//    //Assuming this means N and M items are all multiples of the same item
    @Test
    public void buyNGetMXpercentOffSpecialCanBeAppliedAndDiscountTaken(){
        setupRegister();
        register.addNewItemToPriceList("banana", 1, 0);
        register.addBuyNGetMForXPercentSpecialToItemInPriceList("banana", 2,1, 50);
        register.startTransaction();
        register.scanItem("banana");
        register.scanItem("banana");
        register.scanItem("banana");
        assertEquals(2.5, register.getTotal(), 0.01);

    }
    @Test
    public void XForYSpecialCanBeAppliedAndDiscountTaken(){
        setupRegister();
        register.addNewItemToPriceList("banana", 1, 0);
        register.addXForYSpecialToItemInPricelist("banana", 2, 1);
        register.startTransaction();
        register.scanItem("banana");
        register.scanItem("banana");
        assertEquals(1, register.getTotal(), 0.01);
    }
    @Test
    public void MforNAtXPercentOffSpecialCanBeAppliedToItemByWeight(){
        setupRegister();
        register.addNewItemToPriceList("banana", 1, 0);
        register.addBuyNGetMForXPercentSpecialToItemInPriceList("banana", 2, 1, 50);
        register.startTransaction();
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        assertEquals(2.50, register.getTotal(), 0.01);
    }
//n get m limit
    @Test
        public void mForNAtXpercentOffWithLimit(){
        setupRegister();
        register.addNewItemToPriceList("banana", 1, 0);
        register.addBuyNGetMForXPercentSpecialToItemInPriceList("banana", 2, 1, 50, 3);
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        register.scanItemByWeight("banana", 1.0);
        assertEquals(5.50, register.getTotal(), 0.01);

    }
//x for y limit
@Test
public void XForYSpecialWithLimitCanBeAppliedAndDiscountTaken(){
    setupRegister();
    register.addNewItemToPriceList("banana", 1, 0);
    register.addXForYSpecialToItemInPricelist("banana", 2, 1, 2);
    register.startTransaction();
    register.scanItem("banana");
    register.scanItem("banana");
    register.scanItem("banana");
    register.scanItem("banana");
    assertEquals(3, register.getTotal(), 0.01);
}

//n for m weight, limit





//
//    //Req #4 buy N items get M at x% off
//    //Special should not be applied when too few items bought
//
//    //Req #4 buy N items get M at x% off
//    //Special should be applied also when 6 items are bought
//
//
//    // register/pricelist
//
//
//
//
////  pricelist tests
    PriceList priceList;
    @Before
    public void setupPriceList(){
        priceList = new PriceList();
    }
//
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
//    //Req #4 buy N items get M at x% off
//    //Need to be able to set a special in the pricelist and then retrieve it
    @Test
    public void addKJISpecialAndReturnIt(){
        setupPriceList();
        priceList.addItem("bread loaf", 0.99, 0);
        priceList.setSpecialBuyNGetMForXPercentOff("bread loaf", 3, 1, 50);
        int actual1 = priceList.getSpecialBuyNGetMForXPercentOff("bread loaf").getNumberThatMustBePurchased();
        int actual2 = priceList.getSpecialBuyNGetMForXPercentOff("bread loaf").getNumberDiscounted();
        int actual3 = priceList.getSpecialBuyNGetMForXPercentOff("bread loaf").getPercentOff();
        assertEquals(3, actual1, 0.1);
        assertEquals(1, actual2, 0.1);
        assertEquals(50, actual3, 0.1);
    }
//    //Req #5 X for Y dollars
//    //Need to be able to set a special in the pricelist and then retrieve it
    @Test
    public void addXForYSpecialToPriceListAndReturnIt(){
        setupPriceList();
        priceList.addItem("bread loaf", 0.99, 0);
        priceList.setSpecialXForY("bread loaf", 3, 5);
        int actual1 = priceList.getXForYSpecial("bread loaf").getNumberThatMustBePurchased();
        double actual2 = priceList.getXForYSpecial("bread loaf").getPriceForSet();
        assertEquals(3, actual1, 0.1);
        assertEquals(5, actual2, 0.1);
    }
//
//    //Req #8 Buy K get J equal or less value for I percent off (weighted items)
//
//    //  Item object
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
//    //Req #4 buy N items get M at x% off
//    //need to be able to add specials to the item's information set in the pricelist
    @Test
    public void addSpecialToPriceListItemGetSpecialsReturnsThatSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setSpecialBuyNGetMForXPercentOff(3, 1, 100);
        assertEquals(3, orange.getSpecialBuyNGetMForXPercentOff().getNumberThatMustBePurchased());
        assertEquals(1, orange.getSpecialBuyNGetMForXPercentOff().getNumberDiscounted());
        assertEquals(100, orange.getSpecialBuyNGetMForXPercentOff().getPercentOff(), 0.01);
    }
//    //Req #5 support special X for $Y
    @Test
    public void addXForYdollarsSpecialReturnSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setSpecialXForYDollars(5, 5);
        assertEquals(5, orange.getSpecialXForYDollars().getNumberThatMustBePurchased(), 0.1);
        assertEquals(5, orange.getSpecialXForYDollars().getPriceForSet(), 0.1);
    }
//
    @Test
    public void addBuyKGetJOfEqualOrLesserValueForXpercentOffReturnSpecial(){
        Item orange = new Item("orange", 1.35, 0.10);
        orange.setSpecialBuyNGetMForXPercentOff(5, 3, 50);
        assertEquals(5, orange.getSpecialBuyNGetMForXPercentOff().getNumberThatMustBePurchased(), 0.1);
        assertEquals(3, orange.getSpecialBuyNGetMForXPercentOff().getNumberDiscounted(), 0.1);
        assertEquals(50, orange.getSpecialBuyNGetMForXPercentOff().getPercentOff(), 0.1);


    }
//
//
}
