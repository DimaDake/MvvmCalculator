package com.example.myapplication;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {

    CalculatorModel model = new CalculatorModel();
    private final Random random = new Random();


    @RepeatedTest(1000)
    public void addition() {
        long value1 = random.nextInt();
        model.addition(value1);
        long value2 = random.nextInt();
        model.addition(value2);
        long result = value1 + value2;
        assertEquals(result, model.getResult());
        model.reset();
    }

    @RepeatedTest(1000)
    public void subtraction() {
        long value1 = random.nextInt();
        model.addition(value1);
        long value2 = random.nextInt();
        model.subtraction(value2);
        long result = value1 - value2;
        assertEquals(result, model.getResult());
        model.reset();
    }

    @Test
    public void reset() {
        model.reset();
        assertEquals(0, model.getResult());
    }
}
