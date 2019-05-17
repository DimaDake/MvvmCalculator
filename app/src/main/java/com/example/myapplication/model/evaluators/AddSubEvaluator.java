package com.example.myapplication.model.evaluators;

import com.example.myapplication.model.expressions.ArithmeticExpression;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.example.myapplication.model.expressions.ArithmeticExpression.*;

public class AddSubEvaluator implements Evaluator {

    @Override
    public int evaluate(final ArithmeticExpression expression) {
        Operation operation = Operation.PLUS;
        final Deque<Integer> numbers = new ArrayDeque<>();
        final String str = expression.exportToString();
        int number = 0;

        for (final char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                number = number * 10 + Character.getNumericValue(ch);
            } else {
                numbers.add(number);
                number = 0;
                if (numbers.size() == 2) {
                    numbers.add(operation.apply(numbers.removeFirst(), numbers.removeFirst()));
                }
                operation = Operation.getOperation(ch);
            }
        }
        return operation.apply(numbers.isEmpty() ? 0 :numbers.removeFirst(), number);
    }
}
