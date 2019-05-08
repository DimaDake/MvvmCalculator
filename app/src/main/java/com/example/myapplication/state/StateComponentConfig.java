package com.example.myapplication.state;

import javax.inject.Singleton;

public final class StateComponentConfig {

    private final StateComponent stateComponent;

    private StateComponentConfig (){
        stateComponent = DaggerStateComponent.create();
    }

    private static volatile StateComponentConfig instance;

    public static StateComponentConfig getInstance() {
        StateComponentConfig localInstance = instance;
        if (localInstance == null) {
            synchronized (Singleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StateComponentConfig();
                }
            }
        }
        return localInstance;
    }

    public StateComponent getStateComponent(){
        return stateComponent;
    }
}