package fit.wenchao.kotlinplayground.utils;

import java.io.Serializable;
import java.util.Objects;

public class Pair<K, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected K key;
    protected V value;

    public Pair() {
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair(key, value);
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public String toString() {
        return "Pair [key=" + this.key + ", value=" + this.value + "]";
    }

    public static Pair<String, String> resolve(String str, String separator) {
        int i = str.indexOf(separator);
        if (i != -1) {
            String key = str.substring(0, i);
            String value = str.substring(i + 1);
            return Pair.of(key.trim(), value.trim());
        }
        return Pair.of("", "");
    }

    public int hashCode() {
        return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (!(o instanceof Pair)) {
            return false;
        }
        else {
            Pair<?, ?> pair = (Pair) o;
            return Objects.equals(this.getKey(), pair.getKey()) && Objects.equals(this.getValue(), pair.getValue());
        }
    }

}