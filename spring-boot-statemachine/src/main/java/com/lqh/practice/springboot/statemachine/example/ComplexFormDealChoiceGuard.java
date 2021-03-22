package com.lqh.practice.springboot.statemachine.example;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class ComplexFormDealChoiceGuard implements Guard<ComplexFormStates, ComplexFormEvents> {

    @Override
    public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        System.out.println("ComplexFormDealChoiceGuard!!!!!!!!!!!!!");
        boolean returnValue = false;
        Form form = context.getMessage().getHeaders().get("form", Form.class);

        if ((form.formName == null)||(form.formName.indexOf("坏") > -1)) {
            returnValue = false;
        } else {
            returnValue = true;
        }

        System.out.println(form.toString()+" is "+returnValue);
        return returnValue;
    }

}