import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialTests {
    @Test
    public void getNumberThatMustBePurchasedReturnsNumberUsedInConstructor(){
        Special special = new Special(5);
        int expected = 5;
        assertEquals(expected, special.getNumberThatMustBePurchased());
    }
}
