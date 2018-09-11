import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemOnReceiptTests {
    PriceList priceList;
    Item bread;
    ItemOnReciept breadForThisOrder;
    @Before
    public void setup(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.setSpecial("bread loaf", 1, 1, 50);
        bread = priceList.getItem("bread loaf");
        breadForThisOrder = new ItemOnReciept(bread);
    }

    @Test
    public void specialPriceOfABreadLoafReturns50cent(){
        setup();
        double specialPrice = breadForThisOrder.calculateSpecialPrice();
        assertEquals(0.50, specialPrice, 0.01);
    }
    @Test
    public void getQuantityDiscountedWhenYouBuy1(){
        setup();
        int quantityDiscounted = breadForThisOrder.getQuantityDiscounted();
        assertEquals(0, quantityDiscounted, 0.1);
    }
    @Test
    public void getTotalReturnsDiscount(){
        setup();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        int expected = 2;
        assertEquals(expected, breadForThisOrder.getTotal(), 0.01);
    }
}
