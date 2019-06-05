package com.example.myapplication.model;

import com.example.myapplication.CalculatorModel;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {

    @Test
    public void checkGetSet() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value = 15;
        calculatorModel.setValue(value);
        assertEquals(value, calculatorModel.getValue());
    }

    @Test
    public void checkPlus() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value1 = 10;
        calculatorModel.setValue(value1);
        final int value2 = 15;
        final int result = value1 + value2;
        calculatorModel.plus(value2);
        assertEquals(result, calculatorModel.getValue());
    }

    @Test
    public void checkMinus() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value1 = 20;
        calculatorModel.setValue(value1);
        final int value2 = 25;
        final int result = value1 - value2;
        calculatorModel.minus(value2);
        assertEquals(result, calculatorModel.getValue());
    }

    @Test
    public void checkAllClean() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value = 5;
        calculatorModel.setValue(value);
        calculatorModel.clean();
        assertEquals(0, calculatorModel.getValue());
    }
}
