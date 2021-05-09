package com.lqh.practice.springboot.statemachine.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * 复杂订单状态机构建器
 */
@Component
public class ComplexFormStateMachineBuilder {

    private final static String MACHINEID = "complexFormMachine";

    /**
     * 构建状态机
     *
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public StateMachine<ComplexFormStates, ComplexFormEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ComplexFormStates, ComplexFormEvents> builder = StateMachineBuilder.builder();

        System.out.println("构建复杂表单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(ComplexFormStates.BLANK_FORM)
                .choice(ComplexFormStates.CHECK_CHOICE)
                .choice(ComplexFormStates.DEAL_CHOICE)
                .states(EnumSet.allOf(ComplexFormStates.class));

        builder.configureTransitions()
                // write blank ---> full_form
                .withExternal()
                .source(ComplexFormStates.BLANK_FORM).target(ComplexFormStates.FULL_FORM)
                .event(ComplexFormEvents.WRITE)

                // check full_form ---> check_choice
                .and()
                .withExternal()
                .source(ComplexFormStates.FULL_FORM).target(ComplexFormStates.CHECK_CHOICE)
                // ComplexFormCheckChoiceGuard1 返回true执行下面action，2.2.1版本測試只會執行第一個action
//                .action(new CheckFormChoiceAction(), new CheckFormChoiceAction2())
//                .guard(new ComplexFormCheckChoiceGuard1())
                .event(ComplexFormEvents.CHECK)

                // formname==null ? 22 : 111 3333
                .and()
                .withChoice()
                .source(ComplexFormStates.CHECK_CHOICE)
                .first(ComplexFormStates.CONFIRM_FORM, new ComplexFormCheckChoiceGuard(), new ComplexFormChoiceAction(), new ComplexFormChoiceAction3())
                .last(ComplexFormStates.DEAL_FORM, new ComplexFormChoiceAction2(), new ComplexFormChoiceAction4())

                // submit confirm_form ---> success_form
                .and()
                .withExternal()
                .source(ComplexFormStates.CONFIRM_FORM).target(ComplexFormStates.SUCCESS_FORM)
                .event(ComplexFormEvents.SUBMIT)

                .and()
                .withExternal()
                .source(ComplexFormStates.DEAL_FORM).target(ComplexFormStates.DEAL_CHOICE)
                .event(ComplexFormEvents.DEAL)

                .and()
                .withChoice()
                .source(ComplexFormStates.DEAL_CHOICE)
                .first(ComplexFormStates.FULL_FORM, new ComplexFormDealChoiceGuard())
                .last(ComplexFormStates.FAILED_FORM);
        return builder.build();
    }
}