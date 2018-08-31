import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {
//  transactions
    @Test
    public void whenNewRegisterIsQueriedForTotalItReturns0(){
        Register register = new Register();
        assertEquals(0, register.getTotal());
    }



//  seller pricing api
    @Test
    public void whenNewPriceListIsQueriedSizeIs0(){
        PriceList priceList = new PriceList();
        assertEquals(0, priceList.getNumberOfItems());
    }


//  Item object
    @Test
    public void initializeNewItemWithAPriceQueryForPriceReturnsPrice(){
        Item orange = new Item("orange", 1.35);
        assertEquals(1.35, orange.getPrice(), 0.01);
    }
}
