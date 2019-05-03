package com.example.myapplication.model.evaluators;

import com.example.myapplication.model.expressions.ArithmeticExpression;

public interface Evaluator {
    int evaluate(ArithmeticExpression expression);
}
