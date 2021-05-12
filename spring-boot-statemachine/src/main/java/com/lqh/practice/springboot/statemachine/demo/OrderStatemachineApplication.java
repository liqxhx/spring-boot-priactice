package com.lqh.practice.springboot.statemachine.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * <p> 类描述: OrderStatemachineApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/10 21:45
 * @since 2021/05/10 21:45
 */
@SpringBootApplication
public class OrderStatemachineApplication {
    /**
    * main
    */
    public static void main(String[] args){
       new SpringApplicationBuilder()
       .sources(OrderStatemachineApplication.class)
       .web(WebApplicationType.NONE)
       .bannerMode(Banner.Mode.OFF)
       .run(args);
    }
}

enum OrderStates {
    已提交,
    已支付,
    已完成,
    已取消
}

enum OrderEvents {
    完成, 支付, 取消
}

@Log
@Component
class Runner2 implements ApplicationRunner {
    private final OrderService orderService;

    public Runner2(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Order order = orderService.create(new Date());

        StateMachine<OrderStates, OrderEvents> paymentStateMachine = this.orderService.pay(order.getOrderId(), UUID.randomUUID().toString());
        log.info("after called pay(): " + paymentStateMachine.getState().getId().name());
        log.info("order信息:" + this.orderService.byId(order.getOrderId()));
        log.info("[XXXX - Payment State Machine OBJECT] " + paymentStateMachine);
        log.info("[XXXX - Payment State Machine EXTENDED STATE] " + paymentStateMachine.getExtendedState());
        log.info("[XXXX - Payment State Machine INITIAL STATE] " + paymentStateMachine.getInitialState());

        StateMachine<OrderStates, OrderEvents> fulfilledStatemachine = this.orderService.fulfill(order.getOrderId());
        log.info("after called fulfill(): " + fulfilledStatemachine.getState().getId().name());
        log.info("order信息:" + this.orderService.byId(order.getOrderId()));
    }
}

//@Log
//@Component
//class Runner implements ApplicationRunner {
//    private final StateMachineFactory<OrderStates, OrderEvents> factory;
//
//    public Runner(StateMachineFactory<OrderStates, OrderEvents> factory) {
//        this.factory = factory;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Long orderId = 123459999L;
//        StateMachine<OrderStates, OrderEvents> stateMachine = this.factory.getStateMachine(Long.toString(orderId));
//        stateMachine.getExtendedState().getVariables().putIfAbsent("orderId", orderId);
//        stateMachine.start();
//        log.info("当前状态为：" + stateMachine.getState().getId().name());
//
//        stateMachine.sendEvent(OrderEvents.支付);
//        log.info("当前状态为：" + stateMachine.getState().getId().name());
//
//        Message<OrderEvents> fulfillEvent = MessageBuilder.withPayload(OrderEvents.完成).setHeader("a", "b").build();
//        stateMachine.sendEvent(fulfillEvent);
//        log.info("当前状态为：" + stateMachine.getState().getId().name());
//
//        stateMachine.stop();
//    }
//}

@Entity(name = "orders")
@NoArgsConstructor
@Data
class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private Date datetime;
    private String state;

    public Order(Date ordertime, OrderStates state) {
        this.datetime = ordertime;
        this.setState(state);
    }

    public void setState(OrderStates state) {
        this.state = state.name();
    }

    public OrderStates getState() {
        return OrderStates.valueOf(this.state);
    }
}

@Repository
interface OrderRepository extends JpaRepository<Order, Long> { }

@Service
@Log
class OrderService {
    private final OrderRepository orderRepository;
    private final StateMachineFactory<OrderStates, OrderEvents> factory;

    public OrderService(OrderRepository orderRepository, StateMachineFactory<OrderStates, OrderEvents> factory) {
        this.orderRepository = orderRepository;
        this.factory = factory;
    }

    public Order byId(Long orderId) {
        return this.orderRepository.findById(orderId).get();
    }

    public Order create(Date datetime) {
        return this.orderRepository.save(new Order(new Date(), OrderStates.已提交));
    }
    public StateMachine<OrderStates, OrderEvents> fulfill(Long orderId) {
        StateMachine<OrderStates, OrderEvents> machine = this.build(orderId);

        Message paymentMessage = MessageBuilder.withPayload(OrderEvents.完成)
                .setHeader("orderId", orderId)
                .build();

        machine.sendEvent(paymentMessage);
        return machine;
    }

    public StateMachine<OrderStates, OrderEvents> pay(Long orderId, String paymentConfirmationNumber) {
        StateMachine<OrderStates, OrderEvents> machine = this.build(orderId);

        Message paymentMessage = MessageBuilder.withPayload(OrderEvents.支付)
                .setHeader("orderId", orderId)
                .setHeader("paymentConfirmationNumber", paymentConfirmationNumber)
                .build();

        machine.sendEvent(paymentMessage);

        return machine;
    }

    private StateMachine<OrderStates, OrderEvents> build(Long orderId) {
        Order order = this.orderRepository.findById(orderId).get();
        String orderIdKey = Long.toString(order.getOrderId());
        StateMachine<OrderStates, OrderEvents> sm = this.factory.getStateMachine(orderIdKey);
        sm.stop();

        sm.getStateMachineAccessor().doWithAllRegions(sma -> {
                sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<OrderStates, OrderEvents>() {

                    @Override
                    public Message<OrderEvents> preEvent(Message<OrderEvents> message, StateMachine<OrderStates, OrderEvents> stateMachine) {

                        log.info("[EEEE] PRE EVENT");
                        log.info("[EEEE] MESSAGE " + message);
                        log.info("[EEEE] STATE MACHINE " + stateMachine);

                        return super.preEvent(message, stateMachine);
                    }

                    @Override
                    public StateContext<OrderStates, OrderEvents> preTransition(StateContext<OrderStates, OrderEvents> stateContext) {

                        log.info("[TTTT] PRE TRANSITION:");
                        log.info("[TTTT] STATE CONTEXT " + stateContext);

                        return super.preTransition(stateContext);
                    }

                    @Override
                    public void preStateChange(State<OrderStates, OrderEvents> state, Message<OrderEvents> message, Transition<OrderStates, OrderEvents> transition, StateMachine<OrderStates, OrderEvents> stateMachine) {
                        log.info("=======preStageChange: " );
                        Optional.ofNullable(message).ifPresent(msg -> {
                            Optional.ofNullable(
                                    Long.class.cast(msg.getHeaders().getOrDefault("orderId", -1L)))
                                    .ifPresent(orderId -> {
                                        Order order1 = orderRepository.findById(orderId).get();
                                        order1.setState(state.getId()); // This is the one responsible for changing the state
                                        orderRepository.save(order1);

                                    });
                        });
                    }

                    @Override
                    public StateContext<OrderStates, OrderEvents> postTransition(StateContext<OrderStates, OrderEvents> stateContext) {

                        log.info("[PPPP] POST TRANSITION:");
                        log.info("[PPPP] STATE CONTEXT " + stateContext);

                        return super.postTransition(stateContext);
                    }

                    @Override
                    public void postStateChange(State<OrderStates, OrderEvents> state, Message<OrderEvents> message, Transition<OrderStates, OrderEvents> transition, StateMachine<OrderStates, OrderEvents> stateMachine) {
                        log.info("[DDDD] POST STATE CHANGE");

                        log.info("[DDDD] State: " + state);
                        log.info("[DDDD] Message: " + message);
                        log.info("[DDDD] Transition: " + transition);
                        log.info("[DDDD] State Machine: " + stateMachine);

                        super.postStateChange(state, message, transition, stateMachine);
                    }
                });

                sma.resetStateMachine(new DefaultStateMachineContext<>(order.getState(), null, null, null));
            });
        sm.start();
        return sm;
    }
}

@Log
@Configuration
@EnableStateMachineFactory
class SimpleEnumStatemachineConfiguration extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStates.已提交).target(OrderStates.已支付).event(OrderEvents.支付)
                .and()
                .withExternal().source(OrderStates.已支付).target(OrderStates.已完成).event(OrderEvents.完成)
                .and()
                .withExternal().source(OrderStates.已支付).target(OrderStates.已取消).event(OrderEvents.取消)
                .and()
                .withExternal().source(OrderStates.已提交).target(OrderStates.已取消).event(OrderEvents.取消);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states.withStates()
                .initial(OrderStates.已提交)
                .stateEntry(OrderStates.已提交, ctx -> {
                    Long orderId = Long.class.cast( ctx.getExtendedState().getVariables().getOrDefault("orderId", -1L));
                    log.info("正在进入状态：已提交, orderId:" + orderId);

                })
                .state(OrderStates.已支付)
                .end(OrderStates.已完成)
                .end(OrderStates.已取消);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config) throws Exception {
        StateMachineListenerAdapter<OrderStates, OrderEvents> listener = new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
            @Override
            public void stateChanged(State<OrderStates, OrderEvents> from, State<OrderStates, OrderEvents> to) {
                log.info(String.format("状态改变了(从：%s, 到：%s)", from + "", to + ""));
            }
        };

        config.withConfiguration()
                .autoStartup(false)
                .listener(listener);
    }
}