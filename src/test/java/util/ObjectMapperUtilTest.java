package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectMapperUtilTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Set Up");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tear Down");
    }

    @Test
    public void beautifyJsonToString() {
        System.out.println("Test 1");
    }

    @Test
    public void beautifyFormToJson() {
        System.out.println("Test 2");
    }
}
