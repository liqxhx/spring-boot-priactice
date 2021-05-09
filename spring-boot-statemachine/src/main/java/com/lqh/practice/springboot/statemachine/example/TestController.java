package com.lqh.practice.springboot.statemachine.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {
    @Autowired
    private ComplexFormStateMachineBuilder complexFormStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;

    @GetMapping("/test")
    public void test() throws Exception {

        StateMachine<ComplexFormStates, ComplexFormEvents> stateMachine = complexFormStateMachineBuilder.build(beanFactory);
        System.out.println(stateMachine.getId());

        stateMachine.getStateMachineAccessor().

        Form form1 = new Form();
        form1.setId("111");
        form1.setFormName(new Random(System.currentTimeMillis()).nextBoolean()?"test" : null);


        // 创建流程
        System.out.println("-------------------form1------------------");
        stateMachine.start();

        Message message = MessageBuilder.withPayload(ComplexFormEvents.WRITE).setHeader("form", form1).build();
        stateMachine.sendEvent(message);
        System.out.println("当前状态：" + stateMachine.getState().getId());

        message = MessageBuilder.withPayload(ComplexFormEvents.CHECK).setHeader("form", form1).build();
        stateMachine.sendEvent(message);
        System.out.println("当前状态：" + stateMachine.getState().getId());

//        message = MessageBuilder.withPayload(ComplexFormEvents.DEAL).setHeader("form", form1).build();
//        stateMachine.sendEvent(message);
//        System.out.println("当前状态：" + stateMachine.getState().getId());
//
//        message = MessageBuilder.withPayload(ComplexFormEvents.SUBMIT).setHeader("form", form1).build();
//        stateMachine.sendEvent(message);
//        System.out.println("最终状态：" + stateMachine.getState().getId());
    }
}
