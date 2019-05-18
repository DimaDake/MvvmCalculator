package com.example.myapplication.state;

import com.example.myapplication.model.Calculator;

public interface State {
    void clear(Calculator calculator);
    void digit(Calculator calculator, char key);
    void arithmetic(Calculator calculator, char key);
    void equal(Calculator calculator);
}
