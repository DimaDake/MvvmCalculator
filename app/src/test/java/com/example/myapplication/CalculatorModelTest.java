package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class CalculatorModelTest {

    private CalculatorModel calculatorModel;
    private Random random;

    public CalculatorModelTest() {
        calculatorModel = new CalculatorModel();
        random = new Random(1L);
    }

    private long getRandomValue(boolean randomSign) {
        int sign = (randomSign ? (random.nextBoolean() ? 1 : -1) : 1);
        return random.nextInt() * sign;
    }

    @Test
    public void plusTest() {
        for (int i = 0; i < 100; i++) {
            long left = getRandomValue(true);
            long right = getRandomValue(true);
            calculatorModel.setResult(left);
            calculatorModel.plus(right);
            Assert.assertEquals(left + right, calculatorModel.getResult());
        }
    }

    @Test
    public void minusTest() {
        for (int i = 0; i < 100; i++) {
            long left = getRandomValue(true);
            long right = getRandomValue(true);
            calculatorModel.setResult(left);
            calculatorModel.minus(right);
            Assert.assertEquals(left - right, calculatorModel.getResult());
        }
    }

    @Test
    public void resetTest() {
        long left = getRandomValue(true);
        long right = getRandomValue(true);
        calculatorModel.setResult(left);
        calculatorModel.plus(right);
        calculatorModel.reset();
        Assert.assertEquals(0, calculatorModel.getResult());
    }
}
