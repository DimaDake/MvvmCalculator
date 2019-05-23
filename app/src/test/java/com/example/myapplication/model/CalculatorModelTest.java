package com.example.myapplication.model;

import com.example.myapplication.CalculatorModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalculatorModelTest {

    @Test
    public void correctGetSetValue() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        assertEquals(0, calculatorModel.getValue());
        final int value = 5;
        calculatorModel.setValue(value);
        assertEquals(value, calculatorModel.getValue());
    }

    @Test
    public void correctPlus() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value1 = 24;
        calculatorModel.setValue(value1);
        final int value2 = 21;
        final int result = value1 + value2;
        calculatorModel.plus(value2);
        assertEquals(result, calculatorModel.getValue());
    }

    @Test
    public void correctMinus() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value2 = 112;
        final int result = -value2;
        calculatorModel.minus(value2);
        assertEquals(result, calculatorModel.getValue());
    }

    @Test
    public void correctAllClean(){
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value = 5;
        calculatorModel.setValue(value);
        calculatorModel.plus(value);
        calculatorModel.allClean();
        assertEquals(0,calculatorModel.getValue());

    }
}
