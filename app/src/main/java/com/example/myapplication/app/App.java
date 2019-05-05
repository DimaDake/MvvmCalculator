package com.example.myapplication.app;

import android.app.Application;

import com.example.myapplication.state.DaggerStateComponent;
import com.example.myapplication.state.StateComponent;

public class App extends Application {

    private static App instance;

    private StateComponent stateComponent;


    public static App getInstance() {
        return instance;
    }

    public StateComponent getStateComponent(){
        return stateComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        stateComponent = DaggerStateComponent.create();
    }
}
