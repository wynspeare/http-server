import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {
    @Test
    public void firstTest() {
        MyClass test = new MyClass();
        String expected = "Welcome Caroline";
        String actual = test.greeting();

        assertEquals(expected, actual);
    }
}