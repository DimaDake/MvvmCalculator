package com.example.myapplication.model.expressions;

import androidx.annotation.NonNull;

public class AddSubExpression implements ArithmeticExpression {

    @NonNull
    private final StringBuilder expression = new StringBuilder();
    private char prevSymbol;

    public AddSubExpression(@NonNull final String expression){
        this.expression.append(expression.replaceAll("\\s+",""));
    }

    public AddSubExpression() {
    }

    @Override
    public void setSymbol(final char ch) {
        if (Character.isDigit(ch)) {
            setDigit(ch);
        } else if (isOperation(ch)) {
            final Operation op = Operation.getOperation(ch);
            switch (op) {
                case PLUS:
                    setPlus();
                    break;
                case MINUS:
                    setMinus();
                    break;
            }
        }
    }

    @Override
    public void setExpression(String str) {
        expression.setLength(0);
        expression.append(str);
    }

    @Override
    public String exportToString() {
        return expression.toString();
    }

    @Override
    public boolean isCorrect() {
        for (final char ch : expression.toString().toCharArray()) {
            if (!Character.isDigit(ch) && !isOperation(ch)) {
                return false;
            }
        }
        return expression.length() != 1 || !isOperation(expression.charAt(0));
    }

    private void setDigit(final char ch) {
        if (expression.length() == 1 && expression.charAt(0) == '0') {
            expression.setLength(0);
        }
        prevSymbol = ch;
        expression.append(ch);
    }

    private void setPlus() {
        if (isOperation(prevSymbol) && expression.length() > 0) {
            expression.deleteCharAt(expression.length() - 1);
        }
        prevSymbol = Operation.PLUS.getSymbol();
        expression.append(Operation.PLUS);
    }

    private void setMinus() {
        if (isOperation(prevSymbol) && expression.length() > 0) {
            expression.deleteCharAt(expression.length() - 1);
        }
        prevSymbol = Operation.MINUS.getSymbol();
        expression.append(Operation.MINUS);
    }

    private static boolean isOperation(char ch){
        for (Operation op : Operation.values()) {
            if (op.toString().charAt(0) == ch) {
                return true;
            }
        }
        return false;
    }
}
