import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemOnReceiptTests {
    PriceList priceList;
    Item bread;
    @Before
    public void setup(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.setSpecial("bread loaf", 1, 1, 50);
        bread = priceList.getItem("bread loaf");
    }

    @Test
    public void specialPriceOfABreadLoafReturns50cent(){
        ItemOnReciept breadForThisOrder = new ItemOnReciept(bread);
        double specialPrice = breadForThisOrder.calculateSpecialPrice();
        assertEquals(0.50, specialPrice, 0.01);
    }
}
