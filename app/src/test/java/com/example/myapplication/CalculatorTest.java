package com.example.myapplication;

import com.example.myapplication.model.Calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void adding() {
        assertEquals(5, calculator.result("2+3="));
        assertEquals(10, calculator.result("1+9="));
        assertEquals(19, calculator.result("18+1="));
        assertEquals(25, calculator.result("16+9="));
        assertEquals(59, calculator.result("39+20="));
        assertEquals(101, calculator.result("50+50+1="));
        assertEquals(1003, calculator.result("500+300+200+3="));
        assertEquals(1996, calculator.result("1900+10+10+30+46="));
    }

    @Test
    public void subtraction() {
        assertEquals(60, calculator.result("100-40="));
        assertEquals(20, calculator.result("21-1="));
        assertEquals(179, calculator.result("129+80-30="));
        assertEquals(91, calculator.result("100-7-1-1="));
        assertEquals(746, calculator.result("100+20+1000-374="));
        assertEquals(1132, calculator.result("2000-600-268="));
    }

    @Test
    public void reset(){
        assertEquals(0, calculator.result("C"));
    }
}
