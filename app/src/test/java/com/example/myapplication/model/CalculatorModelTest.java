package com.example.myapplication.model;

import com.example.myapplication.model.evaluators.AddSubEvaluator;
import com.example.myapplication.model.expressions.AddSubExpression;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.example.myapplication.model.expressions.ArithmeticExpression.*;

public class CalculatorModelTest {

    private CalculatorModel calculatorModel;

    @Before
    public void setUp() {
        calculatorModel = new CalculatorModel(new AddSubExpression(), new AddSubEvaluator());
    }

    @Test
    public void calculateExpressions() {
        calculatorModel.setExpression(new AddSubExpression("-40-10+6"));
        assertEquals("-44", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("54+3-897"));
        assertEquals("-840", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("0022 - 02"));
        assertEquals("20", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("-00001 + 1"));
        assertEquals("0", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("-73"));
        assertEquals("-73", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("+8"));
        assertEquals("8", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("19"));
        assertEquals("19", calculatorModel.calculate());
    }

    @Test
    public void expressionsWithSpaces() {
        calculatorModel.setExpression(new AddSubExpression("  1 0  + 2    3 -   3    "));
        assertEquals("30", calculatorModel.calculate());
    }

    @Test
    public void calculatingChain() {
        calculatorModel.setExpression(new AddSubExpression("-40-10+6"));
        assertEquals("-44", calculatorModel.calculate());

        // -440 + 500 = 60
        calculatorModel
                .setSymbol('0')
                .setSymbol(Operation.PLUS.getSymbol())
                .setSymbol('5')
                .setSymbol('0')
                .setSymbol('0');
        assertEquals("60", calculatorModel.calculate());

        // also checking operation replacement
        // 60 - 60 = 0
        calculatorModel
                .setSymbol('+')
                .setSymbol('-')
                .setSymbol('6')
                .setSymbol('0');
        assertEquals("0", calculatorModel.calculate());
    }

    @Test
    public void calculateIncorrectExpressions() {
        calculatorModel.setExpression(new AddSubExpression("-"));
        assertEquals("-", calculatorModel.calculate());

        calculatorModel.setExpression(new AddSubExpression("+$24-8#"));
        assertEquals("+$24-8#", calculatorModel.calculate());
    }
}