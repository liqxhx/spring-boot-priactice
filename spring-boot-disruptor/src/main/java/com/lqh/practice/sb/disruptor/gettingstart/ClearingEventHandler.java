package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.EventHandler;

/**
 * <p> 类描述: ClearingEventHandler
 * <pre>
 * 当通过 Disruptor 传递数据时，对象的寿命可能比预期的要长。
 * 为避免这种情况发生，可能需要在处理后清除事件。
 * 如果您有单个事件处理程序清除同一处理程序中的值就足够了。
 * 如果您有一个事件处理程序链，那么您可能需要在链的末尾放置一个特定的处理程序来处理清除对象
 * </pre
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:26
 * @since 2021/06/05 22:26
 */
public class ClearingEventHandler<T> implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        // Failing to call clear here will result in the
        // object associated with the event to live until
        // it is overwritten once the ring buffer has wrapped
        // around to the beginning.
        Printer.output("\u001B[33mclearing " + event + "\u001B[0m");
        event.clear();
    }
}
