import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialXForYTests {
    @Test
    public void getNumberThatMustBePurchasedGetsNumberUsedInConstructor() {
        SpecialXForY special = new SpecialXForY(5, 5);
        int expected = special.getNumberThatMustBePurchased();
        int actual = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void getPriceForSetUsedInConstructor() {
        SpecialXForY special = new SpecialXForY(5, 5);
        double expected = special.getPriceForSet();
        double actual = 5;
        assertEquals(expected, actual, 0.01);

    }
}