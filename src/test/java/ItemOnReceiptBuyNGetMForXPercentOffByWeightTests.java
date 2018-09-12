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
            priceList.setBuyKGetJEqualorLessForXOffSpecial("bread loaf", 2, 1, 50);
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
            double actual = breadForThisOrder.getSpecialPrice(1.0, bread.getMarkdown(), bread.getPrice(), bread.getBuyKGetJEqualOrLessXOffSpecial().get("percentOffLesserValue"));
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
        priceList.setBuyKGetJEqualorLessForXOffSpecial("bread loaf", 2, 1, 50);
        bread = priceList.getItem("bread loaf");
        breadForThisOrder = new ItemOnReceiptBuyNGetMForXPercentOffByWeight(bread);
    }
        @Test
        public void getTotalOfOneItemDoesntMeetSpecialReturnsPriceMinusMarkdownOfThatItem(){
            setupWithMarkdownAndSpecial();
            breadForThisOrder.addItem(2.0);
            //price should be 1.50
            double actual = breadForThisOrder.getTotalPrice();
            double expected = (1.0*2.0)-0.50;
            assertEquals(expected, actual, 0.01);
        }
    }

