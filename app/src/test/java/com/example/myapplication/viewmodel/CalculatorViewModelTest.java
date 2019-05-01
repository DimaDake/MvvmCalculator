package com.example.myapplication.viewmodel;

import com.example.myapplication.model.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorViewModelTest {

    private CalculatorViewModel viewModel;

    @Before
    public void init() {
        viewModel = new CalculatorViewModel();
    }

    @Test
    public void testClear() {
        viewModel.onAcButtonClicked();
        assertTrue(viewModel.getScreenLiveData().getValue() != null && viewModel.getScreenLiveData().getValue().length() == 0);
    }

}