package xyz.zerxoi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloTest {
    @Test
    public void sayHelloTest() {
        Hello hello = new Hello();
        assertEquals("Hello, Jack.", hello.sayHello("Jack"));
    }
}
