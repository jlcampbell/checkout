import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void whenNewRegisterIsQueriedForTotalItReturns0(){
        Register register = new Register();
        assertEquals(0, register.getTotal());
    }
}
