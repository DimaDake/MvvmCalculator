package com.example.myapplication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.Assert.*;

public class CalculatorModelTest {
    private CalculatorModel calculator = new CalculatorModel();
    private Random random = new Random();

    @RepeatedTest(1000)
    public void addTest() {
        long result = random.nextInt();
        calculator.setValue(result);
        long value = random.nextInt();
        result += value;
        calculator.add(value);
        assertEquals(result, calculator.getValue());
    }

    @RepeatedTest(1000)
    public void minusTest() {
        long result = random.nextInt();
        calculator.setValue(result);
        long value = random.nextInt();
        result -= value;
        calculator.minus(value);
        assertEquals(result, calculator.getValue());
    }

    @Test
    public void addLongTest() {
        long result = Integer.MAX_VALUE;
        calculator.setValue(result);
        result += Integer.MAX_VALUE;
        calculator.add(Integer.MAX_VALUE);
        assertEquals(result, calculator.getValue());
    }

    @Test
    public void minusLongTest() {
        long result = Integer.MIN_VALUE;
        calculator.setValue(result);
        result -= Integer.MAX_VALUE;
        calculator.minus(Integer.MAX_VALUE);
        assertEquals(result, calculator.getValue());
    }

    @Test
    public void reset() {
        calculator.setValue(random.nextInt());
        calculator.clear();
        assertEquals(0, calculator.getValue());
    }
}