import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemOnReceiptBuyNGetMTests {
    PriceList priceList;
    Item bread;
    ItemOnReceiptBuyNGetM breadForThisOrder;
    @Before
    public void setup(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.setSpecialBuyNGetMForXPercentOff("bread loaf", 1, 1, 50);
        bread = priceList.getItem("bread loaf");
        breadForThisOrder = new ItemOnReceiptBuyNGetM(bread);
    }

    @Test
    public void specialPriceOfABreadLoafReturns50cent(){
        setup();
        double specialPrice = breadForThisOrder.calculateSpecialPrice();
        assertEquals(0.50, specialPrice, 0.01);
    }

    @Test
    public void BOGO50AppliedtoOneItemOutOfTwo(){
        setup();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        double expected = 1.5;
        assertEquals(expected, breadForThisOrder.getTotal(), 0.01);
    }
    @Test
    public void BOGO50AppliedtoOneItemOutOfThree(){
        setup();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        double expected = 2.5;
        assertEquals(expected, breadForThisOrder.getTotal(), 0.01);
    }


}
