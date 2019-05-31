package com.example.myapplication;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {

    private CalculatorModel model = new CalculatorModel();
    private final Random random = new Random();

    @RepeatedTest(1000)
    public void addition() {
        int value1 = random.nextInt();
        model.plus(value1);
        int value2 = random.nextInt();
        model.plus(value2);
        assertEquals(value1 + value2, model.getValue());
        model.clean();
    }

    @RepeatedTest(1000)
    public void subtraction() {
        int value1 = random.nextInt();
        model.plus(value1);
        int value2 = random.nextInt();
        model.minus(value2);
        assertEquals(value1 - value2, model.getValue());
        model.clean();
    }

    @Test
    public void reset() {
        model.clean();
        assertEquals(0, model.getValue());
    }
}
