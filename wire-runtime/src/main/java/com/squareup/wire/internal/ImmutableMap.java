package com.squareup.wire.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public final class ImmutableMap<K, V> extends AbstractMap<K, V> implements Serializable {
  final LinkedHashMap<K, V> map;

  ImmutableMap(Map<K, V> map) {
    this.map = new LinkedHashMap<>(map);
  }

  @Override public int size() {
    return map.size();
  }

  @Override public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  @Override public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  @Override public V get(Object key) {
    return map.get(key);
  }

  @NotNull @Override public Set<K> keySet() {
    return map.keySet();
  }

  @NotNull @Override public Collection<V> values() {
    return map.values();
  }

  @Override public Set<Entry<K, V>> entrySet() {
    return map.entrySet();
  }

  private Object writeReplace() throws ObjectStreamException {
    return Collections.unmodifiableMap(map);
  }
}
