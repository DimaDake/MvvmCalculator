package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {
    private CalculatorModel model;
    private Random random;

    @Before
    public void init() {
        model = new CalculatorModel();
        random = ThreadLocalRandom.current();
    }

    @Test
    public void testPlus() {
        final long value1 = random.nextLong();
        model.plus(value1);
        assertEquals(value1, model.getValue());

        final long value2 = random.nextLong();
        model.plus(value2);
        assertEquals(value1 + value2, model.getValue());
    }

    @Test
    public void testMinus() {
        final long value1 = random.nextLong();
        model.setValue(value1);

        final long value2 = random.nextLong();
        model.minus(value2);
        assertEquals(value1 - value2, model.getValue());
    }

    @Test
    public void testPlusAndMinus() {
        final long value1 = random.nextLong();
        model.plus(value1);
        final long value2 = random.nextLong();
        model.minus(value2);
        assertEquals(value1 - value2, model.getValue());
    }

    @Test
    public void testClear() {
        final long value1 = random.nextLong();
        model.setValue(value1);
        assertEquals(value1, model.getValue());
        model.clear();
        assertEquals(0, model.getValue());
    }
}