import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialBuyNGetMForXPercentOffTests {
    @Test
    public void getNumberThatMustBePurchasedGetsNumberUsedInConstructor(){
        SpecialBuyNGetMForXPercentOff special = new SpecialBuyNGetMForXPercentOff(5, 1 , 50);
        int expected = 5;
        int actual = special.getNumberThatMustBePurchased();
        assertEquals(expected, actual);
    }
    @Test
    public void getNumberDiscountedReturnsSecondConstructorArg(){
        SpecialBuyNGetMForXPercentOff special = new SpecialBuyNGetMForXPercentOff(5, 1 , 50);
        int expected = 1;
        int actual = special.getNumberDiscounted();
        assertEquals(expected, actual);
    }
    @Test
    public void getPercentOffReturnsThirdConstructorArg(){

    }
}
