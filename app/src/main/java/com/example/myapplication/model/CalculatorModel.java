package com.example.myapplication.model;

import androidx.annotation.NonNull;

import com.example.myapplication.model.evaluators.Evaluator;
import com.example.myapplication.model.expressions.ArithmeticExpression;

public final class CalculatorModel {

    @NonNull
    private ArithmeticExpression expression;

    @NonNull
    private Evaluator evaluator;

    public CalculatorModel(
            @NonNull final ArithmeticExpression expression,
            @NonNull final Evaluator evaluator) {
        this.expression = expression;
        this.evaluator = evaluator;
    }

    public void setExpression(@NonNull ArithmeticExpression expression) {
        this.expression = expression;
    }

    public void setEvaluator(@NonNull Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public String getExpression() {
        return expression.exportToString();
    }

    public CalculatorModel setSymbol(final char ch) {
        expression.setSymbol(ch);
        return this;
    }

    public void clearExpression() {
        expression.setExpression("");
    }

    public boolean isExpressionCorrect() {
        return expression.isCorrect();
    }

    public String calculate() {
        if (expression.isCorrect()) {
            final int result = evaluator.evaluate(expression);
            expression.setExpression(Integer.toString(result));
        }
        return expression.exportToString();
    }
}
