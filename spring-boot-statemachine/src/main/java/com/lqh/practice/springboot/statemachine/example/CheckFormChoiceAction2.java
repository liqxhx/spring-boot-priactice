package com.lqh.practice.springboot.statemachine.example;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class CheckFormChoiceAction2 implements Action<ComplexFormStates, ComplexFormEvents> {

    @Override
    public void execute(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        System.out.println("222check");
        Form form = context.getMessage().getHeaders().get("form", Form.class);
        System.out.println(form);
        System.out.println(context.getStateMachine().getState());
    }
}