package com.lqh.practice.sb.disruptor.demo.dal;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 类描述: PersistenceHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 23:53
 * @since 2021/06/08 23:53
 */
@Slf4j
public class PersistenceHandler implements WorkHandler<PersistenceEvent>, EventHandler<PersistenceEvent> {
    private final Map<Class, Dao> daoRegistry = new HashMap<>(8);

    public void regist(Class claxx, Dao dao) {
        this.daoRegistry.putIfAbsent(claxx, dao);
    }

    @Override
    public void onEvent(PersistenceEvent event, long sequence, boolean endOfBatch) throws Exception {
        persist(event);
    }

    @Override
    public void onEvent(PersistenceEvent event) throws Exception {
        persist(event);
    }

    public void persist(PersistenceEvent event) {
        StopWatch watch = new StopWatch("persist-consume " + event.toString());
        watch.start();

        switch (event.getOpType()) {
            case PersistenceEvent.OP_INSERT:
                insert(event.getEntity());
                break;
            case PersistenceEvent.OP_DELETE:
                delete(event.getEntity());
                break;
            case PersistenceEvent.OP_UPDATE:
                update(event.getEntity());
                break;
            default:
                break;
        }

        watch.stop();
//        Printer.output("\u001b[32m"+ watch.shortSummary() + "\u001b[0m");
        log.info("{}", watch.shortSummary());
    }

    private void update(Object entity) {
        daoRegistry.get(entity.getClass()).update(entity);
    }

    private void delete(Object entity) {
        daoRegistry.get(entity.getClass()).delete(entity);
    }

    private void insert(Object entity) {
        daoRegistry.get(entity.getClass()).insert(entity);
    }
}
