package com.lqh.practice.springboot.statemachine.example;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ComplexFormChoiceAction4 implements Action<ComplexFormStates, ComplexFormEvents> {

    @Override
    public void execute(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        System.out.println("4444444444444444444444444");
        Form form = context.getMessage().getHeaders().get("form", Form.class);
        System.out.println(form);
        System.out.println(context.getStateMachine().getState());
    }
}