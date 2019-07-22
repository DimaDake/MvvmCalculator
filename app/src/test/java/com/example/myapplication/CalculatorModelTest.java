package com.example.myapplication;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {

    @Test
    public void getSet() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        assertEquals(0, calculatorModel.getValue());
        final int value1 = 1;
        calculatorModel.setValue(value1);
        assertEquals(value1, calculatorModel.getValue());
        final int value2 = 2;
        calculatorModel.setValue(value2);
        assertEquals(value2, calculatorModel.getValue());
    }

    @Test
    public void add() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value1 = 1;
        final int value2 = 2;
        final int res = value1 + value2;
        calculatorModel.setValue(value1);
        calculatorModel.add(value2);
        assertEquals(res, calculatorModel.getValue());
    }

    @Test
    public void sub() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value1 = 1;
        final int value2 = 2;
        final int res = value2 - value1;
        calculatorModel.setValue(value2);
        calculatorModel.sub(value1);
        assertEquals(res, calculatorModel.getValue());
    }

    @Test
    public void clean() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value = 3;
        calculatorModel.setValue(value);
        calculatorModel.clean();
        assertEquals(0, calculatorModel.getValue());
    }
}
