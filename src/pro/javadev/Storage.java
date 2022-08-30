package pro.javadev;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

public interface Storage<K, V> {

    V get(K key);

    default V get(K key, V defaultValue) {
        return ofNullable(get(key)).orElse(defaultValue);
    }

    void set(K key, V value);

    class HashMapStorage implements Storage<String, Object> {


        @Override
        public Object get(String key) {
            return null;
        }

        @Override
        public void set(String key, Object value) {

        }
    }

}
