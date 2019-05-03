package com.example.myapplication.model.expressions;

import androidx.annotation.NonNull;

public interface ArithmeticExpression {
    void setSymbol(char ch);
    void setExpression(String str);
    boolean isCorrect();
    String exportToString();

    enum Operation {
        PLUS('+') {
            @Override
            public int apply(final int x, final int y) {
                return x + y;
            }
        },
        MINUS('-') {
            @Override
            public int apply(final int x, final int y) {
                return x - y;
            }
        };

        private char symbol;

        Operation(final char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        @Override
        @NonNull
        public String toString() {
            return String.valueOf(symbol);
        }

        public static Operation getOperation(final char ch) {
            for(Operation v : values())
                if(v.getSymbol() == ch) return v;
            throw new IllegalArgumentException();
        }

        public abstract int apply(final int x, final int y);
    }
}
