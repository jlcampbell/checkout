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
            breadForThisOrder.addItem(0.5);
            int actual = breadForThisOrder.getNumberOfItems();
            int expected = 1;
            assertEquals(expected, actual);
        }
        @Test
        public void calculateSpecialPriceOf1lbItemAt1DollarPerPoundAt50percentOff(){
            breadForThisOrder.addItem(1.0);
            double actual = breadForThisOrder.getSpecialPrice(1.0, bread.getMarkdown(), bread.getPrice(), bread.getSpecial().get("x"));
            double expected = 1*.5;
            assertEquals(expected, actual, 0.01);
        }
    }

