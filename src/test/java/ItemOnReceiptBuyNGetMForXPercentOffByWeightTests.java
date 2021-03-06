import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemOnReceiptBuyNGetMForXPercentOffByWeightTests {
        PriceList priceList;
        Item bread;
        ItemOnReceiptBuyNGetMForXPercentOffByWeight breadForThisOrder;
        @Before
        public void setup(){
            priceList = new PriceList();
            priceList.addItem("bread loaf", 1, 0);
            priceList.setSpecialBuyNGetMForXPercentOff("bread loaf", 2, 1, 50);
            bread = priceList.getItem("bread loaf");
            breadForThisOrder = new ItemOnReceiptBuyNGetMForXPercentOffByWeight(bread);
        }
        @Test
        public void addOneItemTotalNumberOfItemsIs1(){
            setup();
            breadForThisOrder.addItem(0.5);
            int actual = breadForThisOrder.getNumberOfItems();
            int expected = 1;
            assertEquals(expected, actual);
        }
        @Test
        public void calculateSpecialPriceOf1lbItemAt1DollarPerPoundAt50percentOff(){
            setup();
            breadForThisOrder.addItem(1.0);
            double actual = breadForThisOrder.getSpecialPrice(1.0);
            double expected = 1*.5;
            assertEquals(expected, actual, 0.01);
        }
        @Before
        public void setupWithMarkdown(){
            priceList = new PriceList();
            priceList.addItem("bread loaf", 1, 0.50);
            bread = priceList.getItem("bread loaf");
            breadForThisOrder = new ItemOnReceiptBuyNGetMForXPercentOffByWeight(bread);
        }
        @Test
        public void calculatePriceAfterMarkdownReturnsOriginalPriceMinusMarkdown(){
            setupWithMarkdown();
            breadForThisOrder.addItem(2.0);
            double actual = breadForThisOrder.getOriginalPriceMinusMarkdown(2);
            double expected = (1*2)-0.50;
            assertEquals(expected, actual, 0.01);
        }
    @Before
    public void setupWithMarkdownAndSpecial(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0.50);
        priceList.setSpecialBuyNGetMForXPercentOff("bread loaf", 2, 1, 50);
        bread = priceList.getItem("bread loaf");
        breadForThisOrder = new ItemOnReceiptBuyNGetMForXPercentOffByWeight(bread);
    }
        @Test
        public void getTotalOfOneItemDoesntMeetSpecialReturnsPriceMinusMarkdownOfThatItem(){
            setupWithMarkdownAndSpecial();
            breadForThisOrder.addItem(2.0);
            //price should be 1.50
            double actual = breadForThisOrder.getTotal();
            double expected = (1.0*2.0)-0.50;
            assertEquals(expected, actual, 0.01);
        }
        @Test
        public void getTotalOfTwoItemsDoesntMeetSpecialReturnsPriceMinusMarkdownOfItems(){
            setupWithMarkdownAndSpecial();
            breadForThisOrder.addItem(2.0);
            //price should be 1.50
            breadForThisOrder.addItem(1.0);
            double actual = breadForThisOrder.getTotal();
            double expected = (1.0*2.0)-0.50+(1*1)-0.50;
            assertEquals(expected, actual, 0.01);
        }
        @Test
        public void getTotalOfThreeItemsWhereSpecialIsAppliedToOne(){
            setupWithMarkdownAndSpecial();
            breadForThisOrder.addItem(2.0);
            breadForThisOrder.addItem(1.0);
            breadForThisOrder.addItem(1.0);
            double actual = breadForThisOrder.getTotal();
            double expected = (1.0*2.0)-0.50+(1*1)-0.50+((1*1)-0.50)*0.50;
            assertEquals(expected, actual, 0.01);
        }
        //special is applied twice when six items purchased
    @Test
        public void whenSixItemsArePurchasedSpecialIsAppliedTwice(){
            setupWithMarkdownAndSpecial();
            breadForThisOrder.addItem(2.0);
            breadForThisOrder.addItem(1.0);
            breadForThisOrder.addItem(1.0);
            breadForThisOrder.addItem(2.0);
            breadForThisOrder.addItem(1.0);
            breadForThisOrder.addItem(1.0);
            double actual = breadForThisOrder.getTotal();
            double expected = ((1.0*2.0)-0.50+(1*1)-0.50+((1*1)-0.50)*0.50)*2;
            assertEquals(expected, actual, 0.01);

        }
        //least expensive item is discounted
    @Test
        public void sameSixItemsScannedInAnyOrderReturnSameTotal(){
        setupWithMarkdownAndSpecial();

        breadForThisOrder.addItem(1.0);
        breadForThisOrder.addItem(1.0);
        breadForThisOrder.addItem(2.0);
        breadForThisOrder.addItem(1.0);
        breadForThisOrder.addItem(1.0);
        breadForThisOrder.addItem(2.0);
        double actual = breadForThisOrder.getTotal();
        double expected = ((1.0*2.0)-0.50+(1*1)-0.50+((1*1)-0.50)*0.50)*2;
        assertEquals(expected, actual, 0.01);
    }


        //limit is respected
        //no special causes no problems

    }

