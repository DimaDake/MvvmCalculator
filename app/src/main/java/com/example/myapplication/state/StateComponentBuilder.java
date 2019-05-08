package com.example.myapplication.state;

public final class StateComponentBuilder {

    private final StateComponent stateComponent;

    private StateComponentBuilder(){
        stateComponent = DaggerStateComponent.create();
    }

    private static volatile StateComponentBuilder instance;

    public static StateComponentBuilder build() {
        StateComponentBuilder localInstance = instance;
        if (localInstance == null) {
            synchronized (StateComponentBuilder.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StateComponentBuilder();
                }
            }
        }
        return localInstance;
    }

    public StateComponent get(){
        return stateComponent;
    }
}