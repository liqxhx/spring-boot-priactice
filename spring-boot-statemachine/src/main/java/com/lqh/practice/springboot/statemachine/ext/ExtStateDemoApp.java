package com.lqh.practice.springboot.statemachine.ext;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * <p> 类描述: ExtStateDemoApp
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/13 22:41
 * @since 2021/05/13 22:41
 */
@SpringBootApplication

public class ExtStateDemoApp {
    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder()
                .sources(ExtStateDemoApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}

@Component
@Log
class Runner implements ApplicationRunner {
    private final ExtService extService;
    public Runner (ExtService service) {
        this.extService = service;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        StateMachine<ExtStates, ExtEvents> sm200 = extService.online("1000", "200");
        log.info("online：" + sm200.getState().getId().name());
        log.info("分机当前状态：" + extService.byExtNo("1000"));


        StateMachine<ExtStates, ExtEvents> sm201 = extService.online("1000", "201");
        log.info("online：" + sm200.getState().getId().name());
        log.info("分机当前状态：" + extService.byExtNo("1000"));
    }
}

enum ExtStates {
    离线,
    空闲,
    繁忙;
}

enum ExtEvents {
    上线,
    空闲,
    忙线,
    下线;
}

@EnableStateMachineFactory
@Configuration
@Log
class ExtStateMachineConfiguration extends StateMachineConfigurerAdapter<ExtStates, ExtEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ExtStates, ExtEvents> config) throws Exception {
        config.withConfiguration()
                .autoStartup(false)
                .listener(new StateMachineListenerAdapter<ExtStates, ExtEvents>(){
                    @Override
                    public void stateChanged(State from, State to) {
                        log.info("分机状态改变了：从 " + from +", 到：" + to );
                    }
                });

    }

    @Override
    public void configure(StateMachineStateConfigurer<ExtStates, ExtEvents> states) throws Exception {
        states.withStates()
                .initial(ExtStates.离线)
                .states(EnumSet.allOf(ExtStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ExtStates, ExtEvents> transitions) throws Exception {
        transitions.withExternal()
//                .source(ExtStates.离线).target(ExtStates.空闲).event(ExtEvents.上线).action(new ToIdleAction(extService)).guard(new ExtOnlineGurad(extService))
                .source(ExtStates.离线).target(ExtStates.空闲).event(ExtEvents.上线)
                .and().withExternal()
                .source(ExtStates.空闲).target(ExtStates.繁忙).event(ExtEvents.忙线)
                .and().withExternal()
                .source(ExtStates.繁忙).target(ExtStates.空闲).event(ExtEvents.空闲)
                .and().withExternal()
                .source(ExtStates.空闲).target(ExtStates.离线).event(ExtEvents.下线)
                .and().withExternal()
                .source(ExtStates.繁忙).target(ExtStates.离线).event(ExtEvents.下线);
    }
}

class ToIdleAction implements Action<ExtStates, ExtEvents> {
    ExtService extService;

    public ToIdleAction(ExtService service) {
        this.extService = service;
    }
    @Override
    public void execute(StateContext<ExtStates, ExtEvents> context) {

    }
}

class ExtOnlineGurad implements Guard<ExtStates, ExtEvents> {
    ExtService extService;

    public ExtOnlineGurad(ExtService service) {
        this.extService = service;
    }

    @Override
    public boolean evaluate(StateContext<ExtStates, ExtEvents> context) {
        String ip = String.valueOf( context.getMessage().getHeaders().get("ip"));

        String extNo = String.valueOf( context.getMessage().getHeaders().get("extNo"));
        return false;
    }
}

@Entity(name = "ext")
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Ext {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String extNo;

    private String state200;
    private String state201;
    private String state;

    public void setState200(ExtStates es) {
        this.state200 = es.name();

        switch(es) {
            case 空闲:
                if(this.getState201() == ExtStates.空闲) {
                    this.setState(ExtStates.空闲);
                }
                break;
            case 繁忙:
                if(this.getState201() == ExtStates.空闲) {
                    this.setState(ExtStates.繁忙);
                }
                break;
            case 离线:
            default:
                this.setState(ExtStates.离线);
                break;
        }
    }

    public void setState201(ExtStates es) {
        this.state201 = es.name();

        switch(es) {
            case 空闲:
                if(this.getState200() == ExtStates.空闲) {
                    this.setState(ExtStates.空闲);
                }
                break;
            case 繁忙:
                if(this.getState200() == ExtStates.空闲) {
                    this.setState(ExtStates.繁忙);
                }
                break;
            case 离线:
            default:
                this.setState(ExtStates.离线);
                break;
        }
    }

    public ExtStates getState200() {
        if(this.state200 != null) {
            return ExtStates.valueOf(this.state200);
        }
        return null;
    }


    public ExtStates getState201() {
        if(this.state201 != null) {
            return ExtStates.valueOf(this.state201);
        }
        return null;
    }

    public ExtStates getState() {
        if(this.state != null) {
            return ExtStates.valueOf(this.state);
        }
        return ExtStates.离线;
    }

    private void setState(ExtStates es) {
        this.state = es.name();
    }
}

interface ExtRepository extends JpaRepository<Ext, Long> {
    /**
     * 根据分机号查找分机
     * @param extNo
     * @return
     */
    Ext findByExtNo(String extNo);
}

@Service
@Log
class ExtService {
   private final ExtRepository repository;
   private final StateMachineFactory<ExtStates, ExtEvents> factory;

   public ExtService(ExtRepository repository, StateMachineFactory<ExtStates, ExtEvents> factory) {
       this.repository = repository;
       this.factory = factory;
       init();
   }

    private void init() {
       int n = 10;
       List<Ext> exts = new ArrayList<>(n);
       for(int i = 0 ; i < n ; i++ ) {
           Ext ext = new Ext();
           ext.setExtNo(String.valueOf(1000 + i));
           ext.setState200(ExtStates.离线);
           ext.setState201(ExtStates.离线);
           exts.add(ext);
       }

       this.repository.saveAll(exts);
       log.info("增加" + n +"个分机");
    }

    public StateMachine<ExtStates, ExtEvents> online(String extNo, String ip) {
        StateMachine<ExtStates, ExtEvents> sm = this.build(extNo);

        Message<ExtEvents> onlineEvent = MessageBuilder
                .withPayload(ExtEvents.上线)
                .setHeader("ip", ip)
                .setHeader("extNo", extNo)
                .build();

        sm.sendEvent(onlineEvent);

        return sm;
   }

   public Ext byExtNo(String extNo) {
       return this.repository.findByExtNo(extNo);
   }

    private StateMachine<ExtStates, ExtEvents> build(String extNo) {
        Ext ext = this.repository.findByExtNo(extNo);
        StateMachine<ExtStates, ExtEvents> sm = this.factory.getStateMachine(extNo);
        sm.stop();

        sm.getStateMachineAccessor().doWithAllRegions(sma -> {
            sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<ExtStates, ExtEvents>(){
                @Override
                public void preStateChange(State<ExtStates, ExtEvents> state, Message<ExtEvents> message, Transition<ExtStates, ExtEvents> transition, StateMachine<ExtStates, ExtEvents> stateMachine) {
                    Optional.ofNullable(message).ifPresent(msg -> {
                        Optional.ofNullable(
                                msg.getHeaders().getOrDefault("extNo", "0").toString())
                                .ifPresent(extNo -> {
                                    Ext ext = repository.findByExtNo(extNo);
                                    String ip = String.valueOf(msg.getHeaders().get("ip"));
                                    if("200".equalsIgnoreCase(ip)) {
                                        ext.setState200(state.getId());
                                    } else {
                                        ext.setState201(state.getId());
                                    }
                                    repository.save(ext);
                                });
                    });

                }
            });

            sma.resetStateMachine(new DefaultStateMachineContext<>(ext.getState(), null, null, null));
        });

        sm.start();
        return sm;
    }
}