package xyz.zerxoi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GreetingTest {

    @Test
    public void greetingTest() {
        assertEquals("Hello, Jack. I am Rose.", new Greeting("Rose").greeting("Jack"));
    }
}
