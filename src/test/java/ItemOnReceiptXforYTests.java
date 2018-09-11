import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemOnReceiptXforYTests {
    PriceList priceList;
    Item bread;
    ItemOnRecieptXforY breadForThisOrder;
    @Before
    public void setup(){
        priceList = new PriceList();
        priceList.addItem("bread loaf", 1, 0);
        priceList.setXForYSpecial("bread loaf", 2, 1);
        bread = priceList.getItem("bread loaf");
        breadForThisOrder = new ItemOnRecieptXforY(bread);
    }
    @Test
    public void buyThreeAtTwoForOneReturn2dollars(){
        setup();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        breadForThisOrder.addItem();
        assertEquals(2,breadForThisOrder.getTotal(), 0.01);
    }



}
