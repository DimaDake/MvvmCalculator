package com.example.myapplication.state;

import dagger.Component;

@Component
public interface StateComponent {
    StateX getStateX();
    StateY getStateY();
    AnswerState getAnswerState();
    ActionState getActionState();
}
