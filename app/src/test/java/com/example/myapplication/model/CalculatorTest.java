package com.example.myapplication.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calculator;
    private Random random;

    @Before
    public void init() {
        calculator = new Calculator();
        random = new Random();
    }

    @Test
    public void addTest() {
        calculator.add(10);
        assertEquals(10, calculator.getValue());
    }

    @Test
    public void addLongTest() {
        long value = Integer.MAX_VALUE;
        calculator.setValue(value);
        value += Integer.MAX_VALUE;
        calculator.add(Integer.MAX_VALUE);
        assertEquals(value, calculator.getValue());
    }

    @Test
    public void minusTest() {
        calculator.minus(10);
        assertEquals(-10, calculator.getValue());
    }

    @Test
    public void randomTest() {
        long value = random.nextInt();
        calculator.setValue(value);
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt();
            if (random.nextBoolean()) {
                value += x;
                calculator.add(x);
            } else {
                value -= x;
                calculator.minus(x);
            }
            assertEquals(value, calculator.getValue());
        }
    }
}