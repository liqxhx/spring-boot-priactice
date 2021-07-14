package com.lqh.practice.sb.disruptor.demo.cc;

import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p> 类描述: CallContext
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 23:09
 * @since 2021/06/08 23:09
 */
@ToString
public class CallSession implements Map<String, Object> {
    @Getter
    private String sessionId;
    private final Map<String, Object> attrs = new HashMap<>(32);

    public CallSession(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int size() {
        return this.attrs.size();
    }

    @Override
    public boolean isEmpty() {
        return this.attrs.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.attrs.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.attrs.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return this.attrs.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return this.attrs.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return this.attrs.remove(key);
    }

    @Override
    public void putAll(Map m) {
        this.attrs.putAll(m);
    }

    @Override
    public void clear() {
        this.attrs.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.attrs.keySet();
    }

    @Override
    public Collection<Object> values() {
        return this.attrs.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return this.attrs.entrySet();
    }
}
