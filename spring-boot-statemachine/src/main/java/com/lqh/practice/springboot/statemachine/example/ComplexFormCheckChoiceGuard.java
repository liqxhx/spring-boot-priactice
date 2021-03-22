package com.lqh.practice.springboot.statemachine.example;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class ComplexFormCheckChoiceGuard implements Guard<ComplexFormStates, ComplexFormEvents> {

    @Override
    public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        boolean returnValue = false;
        Form form = context.getMessage().getHeaders().get("form", Form.class);
        if (form.formName == null) {
            returnValue = false;
        } else {
            returnValue = true;
        }
        return returnValue;
    }

}
