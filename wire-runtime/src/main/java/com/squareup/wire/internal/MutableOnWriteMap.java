package com.squareup.wire.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

final class MutableOnWriteMap<K, V> extends AbstractMap<K, V> implements Serializable {
  private final Map<K, V> immutableMap;
  Map<K, V> mutableMap;

  MutableOnWriteMap(Map<K, V> immutableMap) {
    this.immutableMap = immutableMap;
    this.mutableMap = immutableMap;
  }

  @Override public Set<Entry<K, V>> entrySet() {
    return mutableMap.entrySet();
  }

  @Override public int size() {
    return mutableMap.size();
  }

  @Override public boolean isEmpty() {
    return mutableMap.isEmpty();
  }

  @Override public boolean containsValue(Object value) {
    return mutableMap.containsValue(value);
  }

  @Override public boolean containsKey(Object key) {
    return mutableMap.containsKey(key);
  }

  @Override public V get(Object key) {
    return mutableMap.get(key);
  }

  @Override public V put(K key, V value) {
    if (mutableMap == immutableMap) {
      mutableMap = new LinkedHashMap<>(immutableMap);
    }
    return mutableMap.put(key, value);
  }

  @Override public V remove(Object key) {
    if (mutableMap == immutableMap) {
      mutableMap = new LinkedHashMap<>(immutableMap);
    }
    return mutableMap.remove(key);
  }

  @Override public void putAll(Map<? extends K, ? extends V> m) {
    if (mutableMap == immutableMap) {
      mutableMap = new LinkedHashMap<>(immutableMap);
    }
    mutableMap.putAll(m);
  }

  @Override public void clear() {
    if (mutableMap == immutableMap) {
      mutableMap = new LinkedHashMap<>();
    } else {
      mutableMap.clear();
    }
  }

  private Object writeReplace() throws ObjectStreamException {
    return new LinkedHashMap<>(mutableMap);
  }
}
