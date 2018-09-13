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
    }
