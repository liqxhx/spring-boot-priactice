package com.lqh.practice.sb.jpa;

import com.lqh.practice.sb.jpa.model.Coffee;
import com.lqh.practice.sb.jpa.model.CoffeeOrder;
import com.lqh.practice.sb.jpa.model.OrderState;
import com.lqh.practice.sb.jpa.repository.CoffeeOrderRepository;
import com.lqh.practice.sb.jpa.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> 类描述: JPAAppliation
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 21:24
 * @since 2021/05/28 21:24
 */
@SpringBootApplication
@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
public class JPAAppliation implements ApplicationRunner {

    @Autowired
    CoffeeRepository coffeeRepository;

    @Autowired
    CoffeeOrderRepository orderRepository;

    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(JPAAppliation.class, args);
    }
    
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        init();
        find();
    }

    private void find() {
        // 查询所有coffee,并按id倒序打印
        coffeeRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("loading {}", c));

        // 查询最近的top3订单，打印id
        List<CoffeeOrder> orders = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc:{}", getJoinedOrderId(orders));

        // 查询lqh的下单记录，打印id
        orders = orderRepository.findByCustomerOrderById("lqh");
        log.info("findByCustomerOrderById:{}", getJoinedOrderId(orders));

        // org.hibernate.LazyInitializationException without @Transactional
        orders.forEach(order -> {
            log.info("order {}", order.getId());

            order.getItems().forEach( item -> log.info("Item:{}", item));
        });

        orders = orderRepository.findByItems_Name("latte");
        log.info("findByItems_Name:{}", getJoinedOrderId(orders));
    }

    private String getJoinedOrderId(List<CoffeeOrder> list) {
        return list.stream().map(order -> order.getId().toString()).collect(Collectors.joining(", "));
    }

    private void init() {
        // 创建Coffee latte
        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30))
                .build();
        coffeeRepository.save(latte);
        log.info("coffee:{}", latte);

        // 创建Coffee espresso
        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20))
                .build();

        coffeeRepository.save(espresso);
        log.info("coffee:{}", espresso);

        CoffeeOrder order1 = CoffeeOrder.builder()
                .customer("lqh")
                .items(Arrays.asList(latte))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order1);

        CoffeeOrder order2 = CoffeeOrder.builder()
                .customer("lqh")
                .items(Arrays.asList(latte, espresso))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order2);
    }
}
